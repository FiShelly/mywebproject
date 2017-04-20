/**
 * Created by ddxfc on 2016/12/27.
 */
angular.module('dm.try.search', []).directive('searchbar', ['searchService','configService',function (searchService,configService) {
    return {
        restrict: 'E',
        scope: {value: '=ngModel',leftListData:'='},
        template: '<div class="ic-search-wrap"> ' +
        '<input type="text" id="ic-search-kw" ng-model="value"> ' +
        '<button ng-click="search();"><i class="icon-search" id="ic-search-btn"></i>&nbsp;icon' +
        '</button>' +
        '</div>',

        link: function (scope, iElement, iAttrs, ngModelController) {
            scope.search = function () {
                scope.leftListData = searchService.search(scope.value,configService.config.search_highlight);
            }
        }
    };
}]);