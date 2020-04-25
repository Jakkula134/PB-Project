$(function () {

    $('#container').highcharts({
        chart: {
            type: 'pyramid',
            marginRight: 100
        },
        title: {
            text: 'Most followers Count',
            x: -50
        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b> ({point.y:,.0f})',
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                    softConnector: true
                }
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: 'Tweet Count',
            data: [
                ['NDTV',12702164], 
                ['The Times of India',38092759],
                ['AjaTak',9655792],
                ['ABP News',9528027],
                ['Agence France-Presse',3450511],
               ['ZEIT ONLINE',2219959], 
                ['Sony Sports',2036926],
             ['Cicket NDTV',1795659] 
                
                
                
                
            ]
        }]
    });
});