// Tooltip
const tooltipTriggerList = document.querySelectorAll(
  '[data-bs-toggle="tooltip"]'
);
const tooltipList = [...tooltipTriggerList].map(
  (tooltipTriggerEl) => new bootstrap.Tooltip(tooltipTriggerEl)
);

// Initialize the page
$(document).ready(function () {
  fetchCustomerData();
  fetchItemData();
  setDateTime();
  fetchOrderTable();
  generateOrderId();
  updateCartTable();
});

function generateOrderId() {
  const lastRow = $("#orderHistoryTable tr:last");

  if (lastRow.length === 0) {
    $("#orderId").val("ORD-001");
    return;
  }

  const lastId = lastRow.find("td:eq(0)").text().trim(); // Get the last ID from the table
  const num = parseInt(lastId.replace("ORD-", "")) + 1;
  $("#orderId").val(`ORD-${String(num).padStart(3, "0")}`);
}

// Load customer data into the dropdown
function fetchCustomerData() {
  $.ajax({
    url: "http://localhost:8080/api/v1/customer/getAll",
    success: function (response) {
      const $customerSelect = $("#customerId");
      $customerSelect.empty();
      $customerSelect.append('<option value="">Select Customer</option>');
      for (const customer of response.data || response) {
        $customerSelect.append(
          `<option value="${customer.id}" data-name="${customer.name}">${customer.id}</option>`
        );
      }
    },
    error: function (error) {
      console.error("Error fetching customer data:", error);
      showAlert("danger", "Failed to load customer data!");
    },
  });
}

// Load item data into the dropdown
function fetchItemData() {
  $.ajax({
    url: "http://localhost:8080/api/v1/item/getAll",
    success: function (response) {
      const $itemSelect = $("#itemCode");
      $itemSelect.empty();
      $itemSelect.append('<option value="">Select Item</option>');
      for (const item of response.data || response) {
        $itemSelect.append(
          `<option value="${item.itemCode}" data-name="${item.description}" data-price="${item.unitPrice}" data-qty="${item.qtyOnHand}">${item.itemCode}</option>`
        );
      }
    },
    error: function (error) {
      console.error("Error fetching item data:", error);
      showAlert("danger", "Failed to load item data!");
    },
  });
}

// Show customer name when customer is selected
$("#customerId").on("change", function () {
  const selectedOption = $(this).find(":selected");
  const customerName = selectedOption.data("name") || "";
  $("#customerName").val(customerName ? `${customerName}` : "");
});

// Show item name and price when item is selected
$("#itemCode").on("change", function () {
  const selectedOption = $(this).find(":selected");
  const itemName = selectedOption.data("name") || "";
  const itemPrice = selectedOption.data("price") || "";
  const itemQty = selectedOption.data("qty") || "";
  $("#itemName").val(itemName ? `${itemName}` : "");
  $("#itemPrice").val(itemPrice);
  $("#itemQty").val(itemQty);
});

// Calculate total when order quantity changes
$("#orderQty").on("input", function () {
  const qty = parseInt($(this).val()) || 0;
  const price = parseFloat($("#itemPrice").val()) || 0;
  const total = qty * price;
  $("#totalPrice").val(total.toFixed(2));
});

// Save order
function saveOrder() {
  const orderId = $("#orderId").val();
  const dateTime = $("#orderDate").val();
  const customerId = $("#customerId").val();

  if (!orderId || !dateTime || !customerId || cartItems.length === 0) {
    showAlert("error", "Please add items to cart first!");
    return;
  }

  const orderDTO = {
    orderId: orderId,
    dateTime: dateTime,
    customerId: customerId,
    orderDetails: cartItems.map((item) => ({
      orderId: orderId,
      itemCode: item.itemCode,
      qty: item.qty,
      subTotal: item.total,
    })),
  };

  $.ajax({
    url: "http://localhost:8080/api/v1/order/save",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(orderDTO),
    success: function (response) {
      showAlert("success", "Order Placed Successfully!");
      fetchOrderTable();
      clearForm();
      cartItems = []; // Clear cart after successful order
      updateCartTable();
      $("#orderConfirmModal").modal("hide");
    },
    error: function (error) {
      console.error("Error saving order:", error);
      showAlert("error", "Failed to place order! -> " + error);
    },
  });
}

// Fetch and display orders in the table
function fetchOrderTable() {
  $.ajax({
    url: "http://localhost:8080/api/v1/order/getAll",
    success: function (response) {
      const $orderTable = $("#orderTable");
      const $orderHistoryTable = $("#orderHistoryTable");
      $orderTable.empty();
      $orderHistoryTable.empty();

      for (const order of response.data) {
        let totalAmount = 0;
        let itemsList = [];

        for (const detail of order.orderDetails) {
          const unitPrice =
            detail.qty > 0 ? (detail.subTotal / detail.qty).toFixed(2) : 0;
          totalAmount += parseFloat(detail.subTotal);
          itemsList.push(`${detail.itemCode} (${detail.qty})`);

          $orderTable.append(`
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.customerId}</td>
                            <td>${detail.itemCode}</td>
                            <td>${detail.qty}</td>
                            <td>${unitPrice}</td>
                            <td>${detail.subTotal}</td>
                            <td>${order.dateTime}</td>
                        </tr>
                    `);
        }

        // Add to order history table
        $orderHistoryTable.append(`
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.dateTime}</td>
                        <td>${order.customerId}</td>
                        <td>${itemsList.join(", ")}</td>
                        <td>${totalAmount.toFixed(2)}</td>
                    </tr>
                `);
      }
      generateOrderId();
    },
    error: function (error) {
      console.error("Error fetching order data:", error);
      showAlert("error", "Failed to load order data!" + error);
    },
  });
}

// Show toast
function showAlert(type, message) {
  const alertClass = type === "success" ? "bg-success" : "bg-danger";
  const alertHtml = `
            <div class="alert ${alertClass} text-white alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        `;

  $("#alertContainer").append(alertHtml);

  setTimeout(() => {
    $(".alert").alert("close");
  }, 3000);
}

// Set the current date and time
function setDateTime() {
  let now = new Date();
  now.setMinutes(now.getMinutes() + now.getTimezoneOffset() + 660); // Set the timezone to Sri Lanka
  $("#orderDate").val(
    now.toISOString().split("T")[0] +
      "T" +
      now.toISOString().split("T")[1].slice(0, 8)
  );
}

setInterval(setDateTime, 1000); // Update the date-time every second

// Clear the form
function clearForm() {
  $("#orderForm")[0].reset();
  setDateTime();
  cartItems = [];
  updateCartTable();
}

// Cart functionality
let cartItems = [];

$("#addToCartBtn").on("click", function () {
  const itemCode = $("#itemCode").val();
  const itemName = $("#itemName").val();
  const orderQty = parseInt($("#orderQty").val());
  const unitPrice = parseFloat($("#itemPrice").val());
  const total = parseFloat($("#totalPrice").val());
  const availableQty = parseInt($("#itemQty").val());

  // Check if item already exists in cart
  const existingItem = cartItems.find((item) => item.itemCode === itemCode);
  const totalQty = existingItem ? existingItem.qty + orderQty : orderQty;

  if (totalQty > availableQty) {
    showAlert("error", "Total quantity exceeds available stock!");
    return;
  }

  if (existingItem) {
    existingItem.qty += orderQty;
    existingItem.total += total;
  } else {
    cartItems.push({
      itemCode,
      itemName,
      qty: orderQty,
      unitPrice,
      total,
    });
  }

  showAlert("success", "Item added to cart!");
  updateCartTable();
  clearItemSelection();
});

function updateCartTable() {
  const $cartTable = $("#cartTable");
  $cartTable.empty();

  if (cartItems.length === 0) {
    $cartTable.append(`
        <tr>
          <td colspan="6" class="text-center text-muted py-4">
            Cart is empty. Add items to cart.
          </td>
        </tr>
      `);
    $("#placeOrderBtn").attr("disabled", true);
  } else {
    cartItems.forEach((item, index) => {
      $cartTable.append(`
          <tr>
              <td>${item.itemCode}</td>
              <td>${item.itemName}</td>
              <td>${item.qty}</td>
              <td>${item.unitPrice.toFixed(2)}</td>
              <td>${item.total.toFixed(2)}</td>
              <td>
                  <button class="btn btn-action btn-delete btn-sm" onclick="removeFromCart(${index})">
                      <i class="hgi-stroke hgi-delete-02 fs-5"></i>
                  </button>
              </td>
          </tr>
        `);
    });
    $("#placeOrderBtn").attr("disabled", false);
  }
}

function removeFromCart(index) {
  cartItems.splice(index, 1);
  updateCartTable();
}

function clearItemSelection() {
  $("#itemCode").val("");
  $("#itemName").val("");
  $("#itemPrice").val("");
  $("#itemQty").val("");
  $("#orderQty").val("");
  $("#totalPrice").val("");
  $("#orderQtyError").addClass("d-none");
  $("#addToCartBtn").attr("disabled", true);
}

// Order History Search
$("#orderSearchInput").on("input", function () {
  const searchTerm = $(this).val().toLowerCase();
  filterOrderHistory(searchTerm);
});

function filterOrderHistory(searchTerm) {
  $("#orderHistoryTable tr").each(function () {
    const $row = $(this);
    const orderId = $row.find("td:eq(0)").text();

    if (searchTerm === "") {
      // Reset highlighting when search is empty
      $row.find("td").each(function () {
        $(this).html($(this).text());
      });
      $row.show();
      return;
    }

    const matchFound = $row.text().toLowerCase().includes(searchTerm);

    if (matchFound) {
      // Highlight matching text in all columns
      $row.find("td").each(function () {
        const text = $(this).text();
        const highlightedText = text.replace(
          new RegExp(searchTerm, "gi"),
          (match) => `<span class="bg-warning">${match}</span>`
        );
        $(this).html(highlightedText);
      });
      $row.show();
    } else {
      $row.hide();
    }
  });
}

// Order Quantity Validation
$("#orderQty").on("input", function () {
  const orderQty = $("#orderQty").val().trim();
  const itemQty = $("#itemQty").val();

  if (!orderQty || isNaN(orderQty)) {
    $("#orderQtyError")
      .removeClass("d-none")
      .text("Please enter a valid quantity.");
    $("#addToCartBtn").attr("disabled", true);
    return;
  }

  const orderQtyNum = parseInt(orderQty);
  const itemQtyNum = parseInt(itemQty);

  if (orderQtyNum <= 0) {
    $("#orderQtyError")
      .removeClass("d-none")
      .html(
        '<i class="hgi-stroke hgi-alert-circle align-middle"></i> Order quantity must be greater than 0.'
      );
    $("#addToCartBtn").attr("disabled", true);
    return;
  }

  if (orderQtyNum > itemQtyNum) {
    $("#orderQtyError")
      .removeClass("d-none")
      .html(
        '<i class="hgi-stroke hgi-alert-circle align-middle"></i> Insufficient quantity in stock!'
      );
    $("#addToCartBtn").attr("disabled", true);
  } else {
    $("#orderQtyError").addClass("d-none").text("");
    $("#addToCartBtn").attr("disabled", false);
  }
});
