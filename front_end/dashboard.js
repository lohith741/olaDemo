var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://127.0.0.1:8080/ola/request",
    // "url":"http://183.83.147.52:8080/ola/driver/selectRequest?requestId=1L&driverId=1L",
    "method": "GET",
    "headers": {
        "content-type": "application/x-www-form-urlencoded"
    }
}

function sortTable(n,table_name) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById(table_name);
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        /* Check if the two rows should switch place,
        based on the direction, asc or desc: */
        if (dir == "asc") {
          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        } else if (dir == "desc") {
          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        // Each time a switch is done, increase this count by 1:
        switchcount ++;
      } else {
        /* If no switching has been done AND the direction is "asc",
        set the direction to "desc" and run the while loop again. */
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
  }

function gen_table(data) {
    $("#dashboard").html = ''
    var mainContainer = $("#dashboard");
    console.log(mainContainer)
    console.log(data)
    t_head = `<table class="table", id="table_id">
    <thead class="thead-dark">
    <tr>
        <th onclick="sortTable(0,table_id)">Request ID</th>
        <th onclick="sortTable(1,table_id)">Customer ID</th>
        <th onclick="sortTable(2,table_id)">Driver ID</th>
        <th onclick="sortTable(3,table_id)">Status</th>
        <th onclick="sortTable(4,table_id)">Time Elapsed</th>
    </tr>
    </thead>`
    table_body = `<tbody>`
    for (var i = 0; i < data.length; i++) {
        cells_html = []
        var cells = Object.values(data[i])
        for (var j = 0; j < cells.length; j++){
            cells_html.push(`<td>${cells[j]}</td>`)
        }
        cells_html = cells_html.join()
        row_string = '<tr>' + cells_html + '</tr>'
        table_body += row_string
    }
    table_body += '</tbody> </thead>'
    mainContainer.append(t_head+table_body)
}


$(document).ready(function(){

    $.ajax(settings).done(function (response) {
        gen_table(response)
    })

})
