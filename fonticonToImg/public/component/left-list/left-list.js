/**
 * Created by ddxfc on 2016/12/27.
 */
angular.module('dm.try.leftList', []).directive('leftList', ['$sce', 'configService', 'searchService','scrollService', function ($sce, configService, searchService,scrollService) {
    return {
        restrict: 'E',
        scope: {leftListData: '=ngModel', value: '=', curIcon: '='},
        templateUrl: 'component/left-list/left-list.html',

        link: function (scope) {
            scope.$on('ngRepeatFinished', function () {

                scrollService.scrollUtil().init('.left-list-content-body', '.left-list-scroll-bar', '.left-list-scroll-thumb', '.left-list-content-frame');
                scope.jumpScroll = function ($event) {
                    scrollService.scrollUtil().jumpScroll($event);
                };

                var tempDataList = JSON.parse(localStorage.getItem('leftListData'));
                scope.allNum = 0;
                scope.findNum = 0;
                for (var key in tempDataList) {
                    scope.allNum += tempDataList[key]['iconDetail'].length;
                }
                for (key in scope.leftListData) {
                    scope.findNum += scope.leftListData[key]['iconDetail'].length;
                }
            });

            scope.$watch('value', function (newValue) {
                if ((newValue || newValue == '') && configService.config.search.search_realtime) {
                    scope.leftListData = searchService.search(newValue,configService.config.search.search_highlight);
                }
            });

            scope.selectIcon = function (icon,iconType) {
                scope.curIcon = angular.copy(icon);
                scope.curIcon.name = $sce.getTrustedHtml(scope.curIcon.name);
                scope.curIcon.iconType = iconType;

                localStorage.setItem('curIcon',JSON.stringify(scope.curIcon));
            }
        }
    }
}]).directive('repeatFinish', function () {
    return {
        link: function (scope, element, attr) {
            if (scope.$last == true) {
                scope.$emit('ngRepeatFinished');
            }
        }
    }
});