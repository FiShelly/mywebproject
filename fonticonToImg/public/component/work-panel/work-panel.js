/**
 * Created by ddxfc on 2016/12/28.
 */
angular.module('dm.try.workPanel', []).directive('workPanel', ['configService', 'canvasService', 'scrollService', '$parse', function (configService, canvasService, scrollService, $parse) {
    return {
        restrict: 'E',
        scope: {curIcon: '='},
        templateUrl: 'component/work-panel/work-panel.html',

        link: function (scope) {

            scope.config = configService.config;

            scope.iconStatus = JSON.parse(localStorage.getItem('iconStatus'));
            if(!scope.iconStatus){
                scope.iconStatus = [];
                for (var i = 0; i < scope.config.icon.layer_count; i++) {
                    scope.iconStatus.push(angular.copy(configService.tempIconStatus));
                }
            }
            var flag = false;
            Object.keys(scope.config).map(function (key) {
                Object.keys(scope.config[key]).map(function (idx) {
                    scope.$watch('config.' + key + '.' + idx, function (nv) {
                        if (idx == 'layout_count') {
                            changeStatusNumm(scope.config.icon.layer_count);
                        } else if (key == 'global' && flag) {
                            scope.iconStatus = scope.iconStatus.map(function (k) {
                                k[idx] = nv;
                                return k;
                            });
                            flag = true;
                        }
                        configService.saveConfig2Local();
                        draw();
                    });
                })
            });

            scope.$watch('iconStatus', function () {
                draw();
                localStorage.setItem('iconStatus',JSON.stringify(scope.iconStatus));
            }, true);
            scope.$watch('curIcon', function (newValue) {
                if (newValue) {
                    draw()
                }
            });

            var draw = function () {
                var drawCvs = document.querySelector('.ic-draw-cvs');
                var opt = {
                    canvas: drawCvs,
                    status: scope.iconStatus,
                    curIcon: scope.curIcon
                };
                canvasService.initCanvas(opt);
                if (scope.curIcon) {
                    canvasService.draw();
                }
            };

            var changeStatusNumm = function (nv) {
                var a = nv - scope.iconStatus.length;
                if (nv && a < 0) {
                    scope.iconStatus.splice(scope.iconStatus.length + a, -a);
                } else if (nv && a >= 0) {
                    for (var i = 0; i < a; i++) {
                        scope.iconStatus.push(angular.copy(configService.tempIconStatus));
                    }
                }
            };

            scope.clickStatus = function (key, idx) {
                configService.config[key][idx] = !configService.config[key][idx];
                localStorage.setItem('config', JSON.stringify(configService.config));
            };

            scope.changeSetting = function (status) {
                var icwpsbtn = document.querySelector('.ic-work-panel-switch-btn');
                icwpsbtn.classList.remove('a');
                icwpsbtn.classList.remove('b');
                icwpsbtn.classList.add(status);

                var iwpoA = document.querySelector('.ic-work-panel-of.a');
                var iwpoB = document.querySelector('.ic-work-panel-of.b');
                iwpoA.classList.remove('hide');
                iwpoB.classList.remove('hide');
                if (status == 'a') {
                    iwpoB.classList.add('hide');
                } else if (status == 'b') {
                    iwpoA.classList.add('hide');
                }

                initScroll();
            };

            var initScroll = function () {
                var bbodyAll = document.querySelectorAll('.ic-work-panel-of-content-body');
                var barAll = document.querySelectorAll('.ic-work-panel-of-scroll-bar');
                var thumbAll = document.querySelectorAll('.ic-work-panel-of-scroll-thumb');
                var frameyAll = document.querySelectorAll('.ic-work-panel-of-content-frame');

                for (var i = 0; i < bbodyAll.length; i++) {
                    var s = scrollService.scrollUtil();
                    var option = {
                        bbody: bbodyAll[i],
                        bar: barAll[i],
                        thumb: thumbAll[i],
                        frame: frameyAll[i]
                    };
                    s.initUseDoc(option);
                    s.idx = i;
                    (function (idx, ss) {
                        thumbAll[idx].addEventListener('mousedown', function (event) {
                            ss.jumpScroll(event, idx);
                        });
                    })(i, s);
                }

            };
            initScroll();

            scope.selectColor = function (event,index) {
                scope.curElement = event.target;
                scope.index = index;
            };

            var icCanvas = document.querySelector('.ic-color-panel');
            var idCanvas = document.querySelector('.ic-color-direction');
            var iaCanvas = document.querySelector('.ic-color-alpha');
            var l ,t = 0;
            var canvasOption = {
                panelCvs: icCanvas,
                directionCvs: idCanvas,
                alphaCvs: iaCanvas,
                saturation: 100,
                hue: 360,
                alpha: 1
            };

            canvasService.drawColorPanel(canvasOption);

            var createScrollOption = function (className, canvas, flag, fn) {
                var obj = {
                    scrollElement: document.querySelector(className),
                    limitHeight: canvas.height,
                    limitWidth: flag ? canvas.width : 0,
                    cover: document.querySelector('#scroll-cover'),
                    parent: document.querySelector('.ic-color-win'),
                    canvas:canvas,
                    type:flag,
                    callback: fn
                };
                scrollService.add2Queue(obj)
            };

            createScrollOption('.ic-color-adj-d', idCanvas, false, function (top,left) {
                canvasOption.hue = (top / this.limitHeight) * -360;
                canvasService.drawColorPanel(canvasOption);
                getImageData(l,t);
            });

            createScrollOption('.ic-color-adj-a', iaCanvas, false, function (top,left) {
                canvasOption.alpha = 1 - (top / this.limitHeight);
                canvasService.drawColorPanel(canvasOption);
                getImageData(l,t);
            });

            createScrollOption('.ic-color-adj-p', icCanvas, true, function (top, left) {
                l = left;
                t = top;
                getImageData(left,top);
            });

            var getImageData = function (left,top) {
                var imgData = icCanvas.getContext('2d').getImageData(left, top, 1, 1);
                imgData = imgData.data;
                var alp = (imgData[3] / 255).toFixed(2);
                var color = 'rgba(' + imgData[0] + ',' + imgData[1] + ',' + imgData[2] + ',' + alp + ')';
                document.querySelector('#display-color').textContent = color;
                if (scope.curElement ) {
                    scope.curElement.value = color;
                    var idx = 'iconStatus['+scope.index+']';
                    var ngmodel = angular.element(scope.curElement).attr('ng-model');
                    ngmodel = ngmodel.replace('data',idx);
                    $parse(ngmodel).assign(scope, scope.curElement.value);
                    scope.$apply();
                }
            }

        }
    }
}]);