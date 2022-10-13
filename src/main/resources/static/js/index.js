console.log("hello world")
$.ajax({
    url: "/api/car",
    method: 'GET',
    dataType: 'json',
}).done((result)=>{
    data = "";
    console.log(result)
    $.each(result,function(key,item){
        data += `
        <div class="card">
        <img src="${item.gambar}" alt="" id="imgCard" />
        <div class="text">
        <h1><span>${item.brand.name}</span> <span>${item.name}</span></h1>
        <p>${item.type.name}</p>
        <div id="card1">
          <div class="cardContent">
            <img src="asset/plate.png" alt="" id="imgContent">
            <p>Number Plate: <span>${item.plat}</span></p>
          </div>
          <div class="cardContent">
            <img src="asset/dollar.png" alt="" id="imgContent">
            <p>Rentcost: Rp.<span>${item.cost}</span></p>
          </div>
        </div>
      </div>
      </div>
        `;
    })
    $("#crd").html(data);
}).fail((error)=>{
    console.log(error);
})