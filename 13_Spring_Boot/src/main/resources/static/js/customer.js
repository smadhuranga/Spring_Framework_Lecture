// Tooltip
const tooltipTriggerList = document.querySelectorAll(
  '[data-bs-toggle="tooltip"]'
);
const tooltipList = [...tooltipTriggerList].map(
  (tooltipTriggerEl) => new bootstrap.Tooltip(tooltipTriggerEl)
);

const BASE_URL = "http://localhost:8080/api/v1/customer";

$(document).ready(function () {
  loadCustomers();
  generateCustomerId();

  $("#customerForm").on("submit", function (e) {
    e.preventDefault();
    saveCustomer();
  });

  $("#editForm").on("submit", function (e) {
    e.preventDefault();
    updateCustomer();
  });
});

function generateCustomerId() {
  const lastRow = $("#customerTable tr:last");

  if (lastRow.length === 0) {
    $("#id").val("C-001");
    return;
  }

  const lastId = lastRow.find("td:eq(0)").text().trim(); // Get the last ID from the table
  const num = parseInt(lastId.replace("C-", "")) + 1;
  $("#id").val(`C-${String(num).padStart(3, "0")}`);
}

function saveCustomer() {
  const customerData = {
    id: $("#id").val(),
    name: $("#name").val(),
    address: $("#address").val(),
  };

  if (!validateCustomerData(customerData)) {
    return;
  }

  $.ajax({
    url: `${BASE_URL}/save`,
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(customerData),
    success: function (response) {
      showAlert("success", response.message);
      resetForm();
      loadCustomers();
    },
    error: function (xhr) {
      let errorMsg = "Error saving customer";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function loadCustomers() {
  $.ajax({
    url: `${BASE_URL}/getAll`,
    method: "GET",
    contentType: "application/json",
    success: function (response) {
      if (response.status && response.data) {
        const tableBody = $("#customerTable");
        tableBody.empty();

        response.data.forEach(function (customer) {
          tableBody.append(`
                            <tr>
                                <td>${customer.id}</td>
                                <td>${customer.name}</td>
                                <td>${customer.address}</td>
                                <td>
                                    <button class="btn btn-action btn-edit me-2" data-customer-id="${customer.id}">
                                        <i class="hgi-stroke hgi-pencil-edit-02 fs-5"></i>
                                    </button>
                                    <button class="btn btn-action btn-delete" data-customer-id="${customer.id}">
                                        <i class="hgi-stroke hgi-delete-02 fs-5"></i>
                                    </button>
                                </td>
                            </tr>
                        `);
        });

        generateCustomerId();
        attachButtonHandlers();
      }
    },
    error: function (xhr) {
      let errorMsg = "Error loading customers";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function attachButtonHandlers() {
  $(".btn-edit").click(function () {
    const customerId = $(this).data("customer-id");
    editCustomer(customerId);
  });

  $(".btn-delete").click(function () {
    const customerId = $(this).data("customer-id");
    $("#deleteConfirmModal").data("customer-id", customerId);
    $("#deleteConfirmModal").modal("show");
  });
}

function editCustomer(id) {
  $.ajax({
    url: `${BASE_URL}/getAll`,
    method: "GET",
    contentType: "application/json",
    success: function (response) {
      if (response.status && response.data) {
        const customer = response.data.find((c) => c.id === id);
        if (customer) {
          $("#editId").val(customer.id);
          $("#editName").val(customer.name);
          $("#editAddress").val(customer.address);

          new bootstrap.Modal("#editModal").show();
        }
      }
    },
    error: function (xhr) {
      let errorMsg = "Error fetching customer data";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function updateCustomer() {
  const customerData = {
    id: $("#editId").val(),
    name: $("#editName").val(),
    address: $("#editAddress").val(),
  };

  if (!validateCustomerData(customerData)) {
    return;
  }

  $.ajax({
    url: `${BASE_URL}/update`,
    method: "PUT",
    contentType: "application/json",
    data: JSON.stringify(customerData),
    success: function (response) {
      showAlert("success", "Customer updated successfully");
      $("#editModal").modal("hide");
      loadCustomers();
    },
    error: function (xhr) {
      let errorMsg = "Error updating customer";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

$("#confirmDeleteBtn").click(function () {
  const customerId = $("#deleteConfirmModal").data("customer-id");
  deleteCustomer(customerId);
  $("#deleteConfirmModal").modal("hide");
});

function deleteCustomer(id) {
  $.ajax({
    url: `${BASE_URL}/delete/${id}`,
    method: "DELETE",
    contentType: "application/json",
    success: function (response) {
      showAlert("success", response.message);
      loadCustomers();
    },
    error: function (xhr) {
      let errorMsg = "Error deleting customer";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
      }
      showAlert("error", errorMsg);
    },
  });
}

function validateCustomerData(data) {
  if (!data.name || !data.address) {
    showAlert("error", "Please fill in all fields");
    return false;
  }
  if (data.name.length < 5 || !/^[A-Za-z\s]{5,}$/.test(data.name)) {
    showAlert(
      "error",
      "Name must be at least 3 characters and contain only letters and spaces"
    );
    return false;
  }
  if (data.address.length < 5 || !/^[A-Za-z0-9\s]{5,}$/.test(data.address)) {
    showAlert(
      "error",
      "Address must be at least 5 characters and contain only letters, numbers, and spaces"
    );
    return false;
  }
  return true;
}

function resetForm() {
  $("#customerForm")[0].reset();
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
