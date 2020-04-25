google.charts.load('current', {'packages':['geochart']});
google.charts.setOnLoadCallback(drawRegionsMap);

function drawRegionsMap() {

    var data = google.visualization.arrayToDataTable([
        ['Country', 'Count'],
        ['US', 23],
        ['GB', 0],
        ['FR', 06],
        ['CA', 0],
        ['ES', 0],
        ['AU', 3],
        ['ID', 0],
        ['MX', 0],
        ['CM', 0],
        ['AR', 0],
        ['ZA', 0],
        ['NG', 0],
        ['CO', 0],
        ['IN', 39],
        ['MY', 0],
        ['BR', 0],
        ['PK', 50],
        ['AT', 0],
        ['VE', 0],
        ['NL', 0],
    ]);

    var options = {};

    var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

    chart.draw(data, options);
}
