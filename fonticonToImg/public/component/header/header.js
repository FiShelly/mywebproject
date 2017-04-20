/**
 * Created by ddxfc on 2016/12/27.
 */
angular.module('dm.try.header', []).directive('headerbar', ['configService','canvasService', function (configService,canvasService) {
    return {
        restrict: 'E',
        scope: {btns: '=ngModel',curIcon:'='},
        template: '<header class="search-header"> ' +
        '<div class="rt-toolbar"> ' +
        '<div ng-repeat="btn in btns" ng-click="switchClick(btn.click);" class="{{btn.divClass}}" ><i class="{{btn.iClass}}"></i>{{btn.text}}</div> ' +
        '</div><a class="rt-tip-save">点此下载：<span></span> </a>' +
        '</header>',

        link: function (scope) {
            scope.switchClick = function (type) {
                switch (type) {
                    case 'add':
                        add();
                        break;
                    case 'save':
                        save();
                        break;
                }
            };
            var add = function () {
                if(scope.curIcon){
                    var cvs = document.querySelector('.ic-sprite-cvs');
                    canvasService.addSprite(cvs);
                }
            };
            
            var save = function () {
                var cvs = document.querySelector('.ic-sprite-cvs');
                var image = canvasService.canvas2Img(cvs);

                var alink = document.querySelector('.rt-tip-save');
                var fileName = configService.config.icon.filename_prefix+scope.curIcon.name;
                alink.style.display='block';
                alink.style.zIndex='999';
                alink.querySelector('span').textContent = fileName;
                alink.href = image;
                alink.download = fileName+'.'+configService.config.icon.file_type;
                alink.addEventListener('click',function () {
                    this.style.display = 'none';
                });
            }
        }
    }}
    ]
);
