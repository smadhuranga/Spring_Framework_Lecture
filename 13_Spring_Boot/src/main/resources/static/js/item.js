// Tooltip
const tooltipTriggerList = document.querySelectorAll(
  '[data-bs-toggle="tooltip"]'
);
const tooltipList = [...tooltipTriggerList].map(
  (tooltipTriggerEl) => new bootstrap.Tooltip(tooltipTriggerEl)
);

const BASE_URL = "http://localhost:8080/api/v1/item";

$(document).ready(function () {
  loadItems();
  generateItemId();

  $("#itemForm").on("submit", function (e) {
    e.preventDefault();
    saveItem();
  });

  $("#editForm").on("submit", function (e) {
    e.preventDefault();
    updateItem();
  });
});

function generateItemId() {
  const lastRow = $("#itemTable tr:last");

  if (lastRow.length === 0) {
    $("#itemCode").val("I-001");
    return;
  }

  const lastId = lastRow.find("td:eq(0)").text().trim(); // Get the last ID from the table
  const num = parseInt(lastId.replace("I-", "")) + 1;
  $("#itemCode").val(`I-${String(num).padStart(3, "0")}`);
}

function saveItem() {
  const itemData = {
    itemCode: $("#itemCode").val(),
    description: $("#description").val(),
    unitPrice: parseFloat($("#price").val()),
    qtyOnHand: parseInt($("#qty").val()),
  };

  if (!validateItemData(itemData)) {
    return;
  }

  $.ajax({
    url: `${BASE_URL}/save`,
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(itemData),
    success: function (response) {
      showAlert("success", response.message);
      resetForm();
      loadItems();
    },
    error: function (xhr) {
      let errorMsg = "Error saving item";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function loadItems() {
  $.ajax({
    url: `${BASE_URL}/getAll`,
    method: "GET",
    contentType: "application/json",
    success: function (response) {
      if (response.status && response.data) {
        const tableBody = $("#itemTable");
        tableBody.empty();

        response.data.forEach(function (item) {
          tableBody.append(`
                            <tr>
                                <td>${item.itemCode}</td>
                                <td>${item.description}</td>
                                <td>${item.unitPrice.toFixed(2)}</td>
                                <td>${item.qtyOnHand}</td>
                                <td>
                                    <button class="btn btn-action btn-edit me-2" data-item-code="${
                                      item.itemCode
                                    }">
                                        <i class="hgi-stroke hgi-pencil-edit-02 fs-5"></i>
                                    </button>
                                    <button class="btn btn-action btn-delete" data-item-code="${
                                      item.itemCode
                                    }">
                                        <i class="hgi-stroke hgi-delete-02 fs-5"></i>
                                    </button>
                                </td>
                            </tr>
                        `);
        });

        generateItemId();
        attachButtonHandlers();
      }
    },
    error: function (xhr) {
      let errorMsg = "Error loading items";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function attachButtonHandlers() {
  $(".btn-edit").click(function () {
    const itemCode = $(this).data("item-code");
    editItem(itemCode);
  });

  $(".btn-delete").click(function () {
    const itemCode = $(this).data("item-code");
    $("#deleteConfirmModal").data("item-code", itemCode);
    $("#deleteConfirmModal").modal("show");
  });
}

function editItem(itemCode) {
  $.ajax({
    url: `${BASE_URL}/getAll`,
    method: "GET",
    contentType: "application/json",
    success: function (response) {
      if (response.status && response.data) {
        const item = response.data.find((i) => i.itemCode === itemCode);
        if (item) {
          $("#editItemCode").val(item.itemCode);
          $("#editDescription").val(item.description);
          $("#editPrice").val(item.unitPrice.toFixed(2));
          $("#editQty").val(item.qtyOnHand);

          new bootstrap.Modal("#editModal").show();
        }
      }
    },
    error: function (xhr) {
      let errorMsg = "Error fetching item data";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function updateItem() {
  const itemData = {
    itemCode: $("#editItemCode").val(),
    description: $("#editDescription").val(),
    unitPrice: parseFloat($("#editPrice").val()),
    qtyOnHand: parseInt($("#editQty").val()),
  };

  if (!validateItemData(itemData)) {
    return;
  }

  $.ajax({
    url: `${BASE_URL}/update`,
    method: "PUT",
    contentType: "application/json",
    data: JSON.stringify(itemData),
    success: function (response) {
      showAlert("success", "Item updated successfully");
      $("#editModal").modal("hide");
      loadItems();
    },
    error: function (xhr) {
      let errorMsg = "Error updating item";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

// Add handler for confirm delete button
$("#confirmDeleteBtn").click(function () {
  const itemCode = $("#deleteConfirmModal").data("item-code");
  deleteItem(itemCode);
  $("#deleteConfirmModal").modal("hide");
});

// Delete item
function deleteItem(itemCode) {
  $.ajax({
    url: `${BASE_URL}/delete/${itemCode}`,
    method: "DELETE",
    contentType: "application/json",
    success: function (response) {
      showAlert("success", response.message);
      loadItems();
    },
    error: function (xhr) {
      let errorMsg = "Error deleting item";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function validateItemData(data) {
  if (
    !data.itemCode ||
    !data.description ||
    !data.unitPrice ||
    !data.qtyOnHand
  ) {
    showAlert("error", "Please fill in all fields");
    return false;
  }
  if (data.price < 0) {
    showAlert("error", "Price cannot be negative");
    return false;
  }
  if (data.qty < 0) {
    showAlert("error", "Quantity cannot be negative");
    return false;
  }
  return true;
}

function resetForm() {
  $("#itemForm")[0].reset();
}

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
