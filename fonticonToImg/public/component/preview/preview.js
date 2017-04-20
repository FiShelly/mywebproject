/**
 * Created by ddxfc on 2016/12/28.
 */
angular.module('dm.try.preview', []).directive('preview', ['configService','canvasService', function (configService,canvasService) {
    return {
        restrict: 'E',
        scope: {curIcon: '='},
        templateUrl: 'component/preview/preview.html',

        link: function (scope, iElement, iAttrs, ngModelController) {
            scope.config = configService.config;

            scope.$watch('config.icon.layer_count',function (nv, ov) {
                scope.configArray = [];
                for (var i = 0; i < scope.config.icon.layer_count; i++) {
                    scope.configArray.push(i);
                }
            });

        }
    }
}]);