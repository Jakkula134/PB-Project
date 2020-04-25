$(function () {

    $('#container').highcharts({
        chart: {
            type: 'pyramid',
            marginRight: 100
        },
        title: {
            text: 'Top 10 Users Tweeted on each sport',
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
                ['Md Asiqul Islam(cricket)',6], 
                ['Football Goals(soccer)',46],
                ['charlie miller(basket ball)',5],
                ['Al Bat(baseball)',10],
                ['CEV Champions league(volleyball)',3],
                ['Naked Hockey(field hockey)',1],
                ['TheFantasySquirrel(American football)',1],
                ['BeSports Fa(Ice Hockey)',3], 
                ['Buvette d Armandie(Rugby)',6],
                ['テニス大会・草トーナメント・テニス...| (Tennis)',7] 
                
                
            ]
        }]
    });
});