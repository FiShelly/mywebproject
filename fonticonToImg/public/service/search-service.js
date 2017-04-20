/**
 * Created by ddxfc on 2016/12/28.
 */
angular.module('dm.try.service.search', [])
    .factory('searchService', ['$sce',function ($sce) {
        var search =function (newValue,isHighLight) {
            return JSON.parse(window.localStorage.getItem('leftListData')).map(function (val) {
                val.iconDetail = val.iconDetail.map(function (cval) {
                    var flag = cval.name.indexOf(newValue) != -1;
                    if(flag && isHighLight){
                        var regex = new RegExp(newValue, 'gi');
                        cval.name = $sce.trustAsHtml(cval.name.replace(regex, '<span>$&</span>'));
                    } else if(flag){
                        cval.name = $sce.trustAsHtml(cval.name);
                    }
                    if(flag){
                        return cval;
                    }
                }).filter(function (ccval) {
                    return ccval;
                });
                return val;
            });
        };
        return {
            search:search
        }
    }]);