// statr tabel brand
$(document).ready(function () {
    $('#tabel-brand').DataTable({
        ajax: {
            url: '/api/brand',
            dataSrc: ''
        },
        columns: [{
                data: null,
                render: (data, type, row, meta) => {
                    return meta.row + 1
                }
            },
            {
                data: 'name'
            },
            {
                data: null,
                render: (data, type, row, meta) => {
                    return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailBrand" onclick="findByIdBrand(${data.id})">
                    Detail
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editBrand" onClick="editBrand(${data.id})">Edit</button>
                    
                    <button type="button" class="btn btn-danger" onClick="deleteBrand(${data.id})">Delete</button>
                    `;
                }
            }
            
        ]
    });
});

$('#create-brand').click((e) => {
    e.preventDefault()

    let name = $("#brandName").val()
    console.log(name)
    if (name === "" || name === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Please fill all field',
            showConfirmButton: false,
            timer: 1000
        })
    } else {
        $.ajax({
            method: "POST",
            url: "/api/brand",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify({
                name: name
            }),
            success: (result) => {
                $('#createBrand').modal('hide')
                $("#tabel-brand").DataTable().ajax.reload();
                $('#brandName').val()
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Brand has been created',
                    showConfirmButton: false,
                    timer: 2000
                })
            }
        })
    }
})

function findByIdBrand(id) {
    $.ajax({
        method: "GET",
        url: "/api/brand/" + id,
        dataType: "json",
        success: (result) => {
          console.log(result)
            $("#brand-id").text(`${result.id}`)
            $("#brand-name").text(`${result.name}`)
        }
    })
}

function editBrand(id) {
    $.ajax({
      method: 'GET',
      url: '/api/brand/' + id,
      dataType: 'json',
      success: (result) => {
        $('#brandNameEdit').val(`${result.name}`);
        $('#brandIdEdit').val(`${result.id}`);
      },
    });
  }
  
  $('#edit-brand').click((e) => {
    e.preventDefault();
  
    let brandName = $('#brandNameEdit').val();
    let id = $('#brandIdEdit').val();
    $.ajax({
      method: 'PUT',
      url: '/api/brand/' + id,
      dataType: 'json',
      contentType: 'application/json',
      data: JSON.stringify({
        name: brandName,
      }),
      success: (result) => {
        $('#editBrand').modal('hide');
        $('#tabel-brand').DataTable().ajax.reload();
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Region has been updated',
          showConfirmButton: false,
          timer: 2000,
        });
      },
    });
  });

function deleteBrand(id) {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger',
      },
      buttonsStyling: false,
    });
  
    swalWithBootstrapButtons
      .fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true,
      })
      .then((result) => {
        if (result.isConfirmed) {
          $.ajax({
            method: 'DELETE',
            url: '/api/brand/' + id,
            dataType: 'json',
            success: (result) => {
              $('#tabel-brand').DataTable().ajax.reload();
              swalWithBootstrapButtons.fire(
                'Deleted!',
                `Brand ${result.name} has been deleted.`,
                'success'
              );
            },
          });
        } else if (
          result.dismiss === Swal.DismissReason.cancel
        ) {
          swalWithBootstrapButtons.fire(
            'Cancelled',
            'Brand data is safe :)',
            'error'
          );
        }
      });
    }
// end tabel brand
// tabel type
$(document).ready(function () {
  $('#tabel-type').DataTable({
      ajax: {
          url: '/api/type',
          dataSrc: ''
      },
      columns: [{
              data: null,
              render: (data, type, row, meta) => {
                  return meta.row + 1
              }
          },
          {
              data: 'name'
          },
          {
              data: null,
              render: (data, type, row, meta) => {
                  return `
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailType" onclick="findById(${data.id})">
                  Detail
                  </button>

                  <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editType" onClick="editType(${data.id})">Edit</button>
                  
                  <button type="button" class="btn btn-danger" onClick="deleteType(${data.id})">Delete</button>
                  `;
              }
          }
          
      ]
  });
});

$('#create-type').click((e) => {
  e.preventDefault()

  let name = $("#typeName").val()
  console.log(name)
  if (name === "" || name === null) {
      Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Please fill all field',
          showConfirmButton: false,
          timer: 1000
      })
  } else {
      $.ajax({
          method: "POST",
          url: "/api/type",
          dataType: "JSON",
          contentType: "application/json",
          data: JSON.stringify({
              name: name
          }),
          success: (result) => {
              $('#createType').modal('hide')
              $('#tabel-type').DataTable().ajax.reload()
              $('#typeName').val()
              Swal.fire({
                  position: 'center',
                  icon: 'success',
                  title: 'Brand has been created',
                  showConfirmButton: false,
                  timer: 2000
              })
          }
      })
  }
})

function findById(id) {
  $.ajax({
      method: "GET",
      url: "/api/type/" + id,
      dataType: "json",
      success: (result) => {
          $("#type-id").text(`${result.id}`)
          $("#type-name").text(`${result.name}`)
      }
  })
}

function editType(id) {
  $.ajax({
    method: 'GET',
    url: '/api/type/' + id,
    dataType: 'json',
    success: (result) => {
      $('#typeNameEdit').val(`${result.name}`);
      $('#typeIdEdit').val(`${result.id}`);
    },
  });
}

$('#type-brand').click((e) => {
  e.preventDefault();

  let typeName = $('#typeNameEdit').val();
  let id = $('#typeIdEdit').val();
  $.ajax({
    method: 'PUT',
    url: '/api/type/' + id,
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify({
      name: typeName,
    }),
    success: (result) => {
      $('#editType').modal('hide');
      $('#tabel-type').DataTable().ajax.reload();
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Region has been updated',
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

function deleteType(id) {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: 'btn btn-success',
      cancelButton: 'btn btn-danger',
    },
    buttonsStyling: false,
  });

  swalWithBootstrapButtons
    .fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true,
    })
    .then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          method: 'DELETE',
          url: '/api/type/' + id,
          dataType: 'json',
          success: (result) => {
            $('#tabel-type').DataTable().ajax.reload();
            swalWithBootstrapButtons.fire(
              'Deleted!',
              `Type ${result.name} has been deleted.`,
              'success'
            );
          },
        });
      } else if (
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelled',
          'Brand data is safe :)',
          'error'
        );
      }
    });
  }
  // end tabel type
  // start tabel car
  $(document).ready(function () {
    $('#tabel-car').DataTable({
        ajax: {
            url: '/api/car',
            dataSrc: ''
        },
        columns: [{
                data: null,
                render: (data, type, row, meta) => {
                    return meta.row + 1
                }
            },
            {
              data: 'name'
            },
            {
              data: 'cost'
            },
            {
              data: 'plat'
            },
            {
              data: 'type.name'
            },
            {
              data: 'gambar'
            },
            {
              data: 'brand.name'
            },
            {
                data: null,
                render: (data, type, row, meta) => {
                    return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailCar" onclick="findByIdCar(${data.id})">
                    Detail
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editCar" id="editCarList" onClick="editCar(${data.id})">Edit</button>
                    
                    <button type="button" class="btn btn-danger" onClick="deleteCar(${data.id})">Delete</button>
                    `;
                }
            }
            
        ]
    });
});

$("#createCarList").click((e) => {
  let optionType = "";
  $.ajax({
    method: "GET",
    url: "/api/type",
    dataType: "json",
    contentType: "application/json",
    success: (result) => {
      console.log(result);
      result.forEach((type) => {
        console.log(type);
        optionType += `
         <option
          value="${type.name}"
        >
        ${type.name}
        </option>
          `;
      });
      $("#type-list").html(optionType);
    },
  });
  let optionBrand = "";
  $.ajax({
    method: "GET",
    url: "/api/brand",
    dataType: "json",
    contentType: "application/json",
    success: (result) => {
      console.log(result);
      result.forEach((brand) => {
        console.log(brand);
        optionBrand += `
         <option
          value="${brand.name}"
          text="${brand.name}"
        >
        ${brand.name}
        </option>
          `;
      });
      $("#brand-list").html(optionBrand);
    },
  });
});


$("#create-car").click((e) => {
  e.preventDefault();

  let name = $("#carName").val();
  let cost = $("#carCost").val();
  let plat = $("#carPlate").val();
  let image = $("#carImage").val();
  let idType = $("#type-list").val();
  let idBrand = $("#brand-list").val();
  console.log(name);
  if (name === "" || name === null || cost === "" || cost === null || plat === "" || plat === null) {
    Swal.fire({
      position: "center",
      icon: "error",
      title: "Please fill all field",
      showConfirmButton: false,
      timer: 1000,
    });
  } else {
    $.ajax({
      method: "POST",
      url: "/api/car",
      dataType: "JSON",
      contentType: "application/json",
      data: JSON.stringify({
        name: name,
        cost: cost,
        plat: plat,
        gambar: image,
        typeName: idType,
        brandName: idBrand,
      }),
      success: (result) => {
        $("#createCar").modal("hide");
        $("#tabel-car").DataTable().ajax.reload();
        $("#carName").val("");
        $("#carCost").val("");
        $("#carPlate").val("");
        $("#carImage").val("");
        $("#type-list").val("");
        $("#brand-list").val("");
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Country has been created",
          showConfirmButton: false,
          timer: 2000,
        });
      },
    });
  }
});

// $("#editCarList").click((e) => {
//   let optionType = "";
//   $.ajax({
//     method: "GET",
//     url: "/api/type",
//     dataType: "json",
//     contentType: "application/json",
//     success: (result) => {
//       console.log(result);
//       result.forEach((type) => {
//         console.log(type);
//         optionType += `
//          <option
//           value="${type.name}"
//         >
//         ${type.name}
//         </option>
//           `;
//       });
//       $("#type-list-edit").html(optionType);
//     },
//   });
//   let optionBrand = "";
//   $.ajax({
//     method: "GET",
//     url: "/api/brand",
//     dataType: "json",
//     contentType: "application/json",
//     success: (result) => {
//       console.log(result);
//       result.forEach((brand) => {
//         console.log(brand);
//         optionBrand += `
//          <option
//           value="${brand.name}"
//         >
//         ${brand.name}
//         </option>
//           `;
//       });
//       $("#brand-list-edit").html(optionBrand);
//     },
//   });
// });

function editCar(id) {
  $.ajax({
    method: 'GET',
    url: '/api/car/' + id,
    dataType: 'json',
    success: (result) => {
      $('#carIdEdit').val(`${result.id}`);
      $('#carNameEdit').val(`${result.name}`);
      $('#carImageEdit').val(`${result.gambar}`);
      $('#carCostEdit').val(`${result.cost}`);
      $('#carPlateEdit').val(`${result.plat}`);

    },
  });
  let optionType = "";
  $.ajax({
    method: "GET",
    url: "/api/type",
    dataType: "json",
    contentType: "application/json",
    success: (result) => {
      console.log(result);
      result.forEach((type) => {
        console.log(type);
        optionType += `
         <option
          value="${type.name}"
        >
        ${type.name}
        </option>
          `;
      });
      $("#type-list-edit").html(optionType);
    },
  });
  let optionBrand = "";
  $.ajax({
    method: "GET",
    url: "/api/brand",
    dataType: "json",
    contentType: "application/json",
    success: (result) => {
      console.log(result);
      result.forEach((brand) => {
        console.log(brand);
        optionBrand += `
         <option
          value="${brand.name}"
        >
        ${brand.name}
        </option>
          `;
      });
      $("#brand-list-edit").html(optionBrand);
    },
  });

  $("#edit-car").click((e) => {
    e.preventDefault();
  
    let name = $("#carNameEdit").val();
    let cost = $("#carCostEdit").val();
    let plat = $("#carPlateEdit").val();
    let image= $("#carImageEdit").val();
    let idType = $("#type-list-edit").val();
    let idBrand = $("#brand-list-edit").val();
    console.log(name);
    if (name === "" || name === null || cost === "" || cost === null || plat === "" || plat === null) {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Please fill all field",
        showConfirmButton: false,
        timer: 1000,
      });
    } else {
      $.ajax({
        method: "PUT",
        url: "/api/car/" + id,
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify({
          name: name,
          cost: cost,
          plat: plat,
          gambar: image,
          typeName: idType,
          brandName: idBrand,
        }),
        success: (result) => {
          $("#editCar").modal("hide");
          $("#tabel-car").DataTable().ajax.reload();
          // $("#carName").val("");
          // $("#carCost").val("");
          // $("#carPlate").val("");
          // $("#type-list").val("");
          // $("#brand-list").val("");
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Country has been created",
            showConfirmButton: false,
            timer: 2000,
          });
        },
      });
    }
  });
}

function findByIdCar(id) {
  $.ajax({
      method: "GET",
      url: "/api/car/" + id,
      dataType: "json",
      success: (result) => {
        console.log(result)
        $("#car-id").text(`${result.id}`)
        $("#car-name").text(`${result.name}`)
        $("#car-cost").text(`${result.cost}`)
        $("#car-plate").text(`${result.plat}`)
        $("#car-type").text(`${result.type.name}`)
        $("#car-brand").text(`${result.brand.name}`)
        $("#img").attr("src",result.gambar)
      }
  })
}

function deleteCar(carId) {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: 'btn btn-success',
      cancelButton: 'btn btn-danger',
    },
    buttonsStyling: false,
  });

  swalWithBootstrapButtons
    .fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true,
    })
    .then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          method: 'DELETE',
          url: '/api/car/' + carId,
          dataType: 'JSON',
        })
          .done((result) => {
            $('#tabel-car').DataTable().ajax.reload();
            swalWithBootstrapButtons.fire(
              'Deleted!',
              `Car ${result.name} has been deleted.`,
              'success'
            );
          })
          .fail((error) => {
            console.log(error);
          });
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        swalWithBootstrapButtons.fire(
          'Cancelled',
          'Car data is safe :)',
          'error'
        );
      }
    });
}
// end tabel car

 // start tabel booking
 $(document).ready(function () {
  $('#tabel-rental').DataTable({
      ajax: {
          url: '/api/rental',
          dataSrc: ''
      },
      columns: [{
              data: null,
              render: (data, type, row, meta) => {
                  return meta.row + 1
              }
          },
          {
            data: 'profile.name'
          },
          {
            data: 'car.name'
          },
          {
            data: 'rentalDate'
          },
          {
            data: 'returnDate'
          },
          {
            data: 'rentCost'
          },
          {
            data: 'status'
          },
          {
              data: null,
              render: (data, type, row, meta) => {
                  return `
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailCar" onclick="findByIdCar(${data.id})">
                  Detail
                  </button>

                  <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editCar" id="editCarList" onClick="editCar(${data.id})">Edit</button>
                  
                  <button type="button" class="btn btn-danger" onClick="deleteCar(${data.id})">Delete</button>
                  `;
              }
          }
          
      ]
  });
});
