/**
 * Created by ddxfc on 2016/12/28.
 */
angular.module('dm.try.spriteWin', []).directive('spriteWin', ['configService', 'canvasService', function (configService, canvasService) {
    return {
        restrict: 'E',
        scope: {curIcon: '='},
        templateUrl: 'component/sprite-win/sprite-win.html',

        link: function (scope) {

            scope.canvasQueue = canvasService.canvasQueue;

            scope.removeSprite = function (idx) {
                var cvs = document.querySelector('.ic-sprite-cvs');
                canvasService.removeSpriteByIdx(idx,cvs);
            }
        }
    }
}]);