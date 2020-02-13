
function get_settings(id,x,y){
    var settings = {
        "async": true,
        "crossDomain": true,
        // "url": "http://127.0.0.1:8080/ola/customer/"+id,
        "url": "http://127.0.0.1:8080/ola/customer/rideRequest?customerId="+id+"&x="+x+"&y="+y,
        "method": 'POST',
        "headers": {
            "content-type": "application/JSON"
        }
    }
    return settings
}


function showDiv() {
    // $('#driver_board').toggle();
    id = $('#customer_id').val()
    x = $('#x').val()
    y = $('#y').val()
    console.log("testing",id,x,y);
    var settings=get_settings(id,x,y);
    var data = $.ajax(settings).done(function (response) {
      console.log(response)
            alert(response.message)
        })
 }
