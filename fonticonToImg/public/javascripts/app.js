/**
 * Created by ddxfc on 2017/1/3.
 */
(function (angular) {
    angular.module('dmTry',
        ['dm.try.header',
            'dm.try.leftList',
            'dm.try.search',
            'dm.try.preview',
            'dm.try.workPanel',
            'dm.try.service.config',
            'dm.try.service.search',
            'dm.try.service.canvas',
            'dm.try.spriteWin',
            'dm.try.service.scroll',
        ]).controller('AppController', ['$scope', '$http', '$sce', 'configService', function ($scope, $http, $sce, configService) {

        var stringToTrustHtml = function (data) {
            return data.map(function (val) {
                val.iconDetail.map(function (cvl) {
                    cvl.name = $sce.trustAsHtml(cvl.name);
                    return cvl;
                });
                return val;
            });
        };

        $http.get('/component/header/btndata.json', {}).success(function (data) {
            $scope.btns = data.data;
        }).error(function (data) {
        });
//            window.localStorage.setItem('leftListData', '');
        var lldata = window.localStorage.getItem('leftListData');
        if (lldata) {
            $scope.leftListData = stringToTrustHtml(JSON.parse(lldata));
        } else {
            var all = configService.changeData();
            window.localStorage.setItem('leftListData', JSON.stringify(all));
            all = stringToTrustHtml(all);
            $scope.leftListData = all;
        }

        $scope.curIcon = JSON.parse(window.localStorage.getItem('curIcon'));
    }]);

})(angular, window);