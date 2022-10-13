  $(document).ready(function(){
    LoadCarNameList();
    
    $("#carName-list").change(function(){
        let selected = $(this).children("option:selected").data("costbanget");
        let start = $('#start').val();
        let end = $('#end').val();
    
        let startDay = new Date(start);
        let endDay = new Date(end);
        let millisecondsPerDay = 1000 * 60 * 60 * 24;
    
        let millisBetween = endDay.getTime() - startDay.getTime();
        let days = Math.floor(millisBetween / millisecondsPerDay);
        let coba = days * selected;
        console.log(coba)
        $("#costID").val(coba);
    });
    
  });

  function LoadCarNameList(){
    let optionType = ` <option class="text-black" id="optionID">--PILIH MOBIL--</option>`;
    $.ajax({
      method: "GET",
      url: "/api/car",
      dataType: "json",
      contentType: "application/json",
      success: (result) => {
        console.log(result);
        result.forEach((car) => {
          console.log(car.name);
          optionType += `
           <option class="text-black" id="optionID"
            value="${car.name}" data-costbanget="${car.cost}"
          >
          ${car.name}
          </option>
            `;
        });
        $("#carName-list").html(optionType);
      },
    });
  }

// function showDays(){

//     let start = $('#start').val();
//     let end = $('#end').val();

//     let startDay = new Date(start);
//     let endDay = new Date(end);
//     let millisecondsPerDay = 1000 * 60 * 60 * 24;

//     let millisBetween = endDay.getTime() - startDay.getTime();
//     let days = Math.floor(millisBetween / millisecondsPerDay);

//      // Round down.
// }
$("#create-car").click((e) => {
    e.preventDefault();
  
    let carBrand = $("#carName-list").val();
    let startDate = $("#start").val();
    let endDate = $("#end").val();
    let  cost= $("#costID").val();
    let status= $("#status").val();
    if (carBrand === "" || carBrand === null || startDate === "" || startDate === null || endDate === "" || endDate === null) {
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
        url: "/api/rental",
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