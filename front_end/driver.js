function gen_list(data) {
    var mainContainer = $("#driver_board");
    // for (var i = 0; i < data.length; i++) {
    //     var div = document.createElement("div");
    //     div.innerHTML = 'Name: ' + data[i].firstName + ' ' + data[i].lastName;
    //     console.log(div)
    //     mainContainer.append(div);
    // }
    $("#driver_board").html("");
      var mainContainer = $("#driver_board");
    console.log(mainContainer)
    for (var key in data){
        console.log( 'key',key, data[key]);

            var list_items = []
            for (var i = 0; i < data[key].length; i++) {
                var req_time = ''
                var pickup_time = ''
                var completed_time = ''

                var list_item = `<ul style="list-style-type:none;"> <li> <div>`
                var trip_data =  `<span><h5>Customer ID:${data[key][i]['customerId']}</h5></span>
                                    <span><h7>Request ID:${data[key][i]['requestId']}</h7></span>
                                    <span><br>Requested ${data[key][i]['requestedTime']} ago</span>`
                if (key == 'ongoing' || key == 'completed' ){
                  console.log( 'ongoing');
                    trip_data = trip_data + `<span><br>Picked up ${data[key][i]['pickedTime']} ago</span>`
                }
                 if (key == 'completed'){
                  console.log( 'completed');
                    trip_data = trip_data + `<span><br>Completed ${data[key][i]['completedTime']} ago</span>`
                }

                var tail = ` </div></li></ul>`
                var select_button = `<button type="button" class="btn" id="btn12" onclick=select(${data[key][i]['requestId']}) style="float: right">Select</button>`
                list_items.push(list_item+trip_data+tail)
                if (key == 'waiting'){
                    list_items.push(select_button)
                }
            }
            list_items =list_items.join();
            var status_block = `<div class="col-sm-4 panel"> <div class="panel-heading text-center"> <h2> ${key.toUpperCase()}
            </h2> </div> ${list_items} </div>`
            mainContainer.append(status_block);



    }

}

function httpGet(url) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.send();

    xhr.onreadystatechange = processRequest;
     
    function processRequest(e) {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = JSON.parse(xhr.responseText);

                return response
            }
        }
}

function get_settings(id){
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://127.0.0.1:8080/ola/driver/"+id,
        // "url":"http://183.83.147.52:8080/ola/driver/selectRequest?requestId=1L&driverId=1L",
        "method": "GET",
        "headers": {
            "content-type": "application/JSON"
        }
    }
    return settings
}

function select_settings(driverId,requestId){
  var settings = {
      "async": true,
      "crossDomain": true,
      "url": "http://127.0.0.1:8080/ola/driver/selectRequest?driverId="+driverId+"&requestId="+requestId,
      // "url":"http://183.83.147.52:8080/ola/driver/selectRequest?requestId=1L&driverId=1L",
      "method": "POST",
      "headers": {
          "content-type": "application/JSON"
      }
  }
  return settings
}



$(document).ready(function(){
    $("#btn1").click(function(){
      console.log( $("#btn1").val())
    $.ajax(settings).done(function (response) {
            // alert(response);
        })
    });
  });

function showDiv() {
    // $('#driver_board').toggle();
    id = $('#driver_id').val()
    console.log("testing",id);
    var settings=get_settings(id);
    var data = $.ajax(settings).done(function (response) {
            console.log('dfgd',response);
            gen_list(data.responseJSON)
        })
 }

 function select(id){
   console.log(id);
   driverid = $('#driver_id').val()
   console.log("testing",driverid);
   var settings=select_settings(driverid,id);
   var data = $.ajax(settings).done(function (response) {
           console.log(response);
            alert(response.message)
       })

    $("#driver_board").html("");


   // location.reload(true)
 }
