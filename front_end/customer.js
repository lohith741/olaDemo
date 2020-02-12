
function get_settings(id,method){
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://127.0.0.1:8080/ola/customer/"+id,
        // "url":"http://183.83.147.52:8080/ola/driver/selectRequest?requestId=1L&driverId=1L",
        "method": method,
        "headers": {
            "content-type": "application/JSON"
        }
    }
    return settings
}


function showDiv() {
    // $('#driver_board').toggle();
    id = $('#customer_id').val()
    console.log("testing",id);
    var settings=get_settings(id,'POST');
    var data = $.ajax(settings).done(function (response) {
      console.log(response)
            alert(response.message)
        })
 }
