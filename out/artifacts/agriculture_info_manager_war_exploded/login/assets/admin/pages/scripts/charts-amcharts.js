const ChartsAmcharts = function () {

    const initChartSample1 = function (json) {
        console.log(json)
        const chart = AmCharts.makeChart("chart_1", {
            "type": "serial",
            "theme": "light",
            "pathToImages": Metronic.getGlobalPluginsPath(),
            "autoMargins": false,
            "marginLeft": 30,
            "marginRight": 8,
            "marginTop": 10,
            "marginBottom": 26,

            "fontFamily": 'Open Sans',
            "color": '#888',

            "dataProvider": [{
                "year": 2001,
                "income": json.sumNum,
                "expenses": json.sumNum
            }, {
                "year": 2010,
                "income": json.manager,
                "expenses":json.manager,
            }, {
                "year": 2011,
                "income": json.generalManager,
                "expenses": json.generalManager
            }, {
                "year": 2012,
                "income": json.generalUser,
                "expenses":json.generalUser
            }, {
                "year": 2013,
                "income": json.applyNum,
                "expenses": json.applyNum,

            }],
            "startDuration": 1,
            "graphs": [{
                "alphaField": "alpha",
                "balloonText": "<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b> [[additional]]</span>",
                "dashLengthField": "dashLengthColumn",
                "fillAlphas": 1,
                "title": "Income",
                "type": "column",
                "valueField": "income"
            }, {
                "balloonText": "<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b> [[additional]]</span>",
                "bullet": "round",
                "dashLengthField": "dashLengthLine",
                "lineThickness": 3,
                "bulletSize": 7,
                "bulletBorderAlpha": 1,
                "bulletColor": "#FFFFFF",
                "useLineColorForBulletBorder": true,
                "bulletBorderThickness": 3,
                "fillAlphas": 0,
                "lineAlpha": 1,
                "title": "Expenses",
                "valueField": "expenses"
            }],
            "categoryField": "year",
            "categoryAxis": {
                "gridPosition": "start",
                "axisAlpha": 0,
                "tickLength": 0
            }
        });

        $('#chart_1').closest('.portlet').find('.fullscreen').click(function () {
            chart.invalidateSize();
        });
    };
    return {
        //main function to initiate the module

        init: function (json) {
            initChartSample1(json);
        }

    };

}();