/**
 * Created by ddxfc on 2016/12/29.
 */
angular.module('dm.try.service.canvas', [])
    .factory('canvasService', ['configService', function (configService) {

        var config;
        var status;
        var canvas;
        var ctx;
        var curIcon;
        var canvasQueue = [];

        var initCanvas = function (option) {
            config = configService.config;
            status = option.status;
            canvas = option.canvas;
            curIcon = option.curIcon;
            canvas.width = config.icon.width;
            canvas.height = config.icon.height * config.icon.layer_count;
            ctx = canvas.getContext('2d');
        };

        var initDrawGlobalIcon = function (i) {
            if (config.icon.show_line && i != 0) {
                ctx.beginPath();
                ctx.strokeStyle = config.icon.line_color;
                ctx.moveTo(0, config.icon.height * i);
                ctx.lineTo(config.icon.width, config.icon.height * i);
                ctx.stroke();
            }
        };

        var initDrawStatus = function (status, i) {
            ctx.fillStyle = status.icon_backgroundcolor;
            ctx.fillRect(0, config.icon.height * i, config.icon.width, config.icon.height);
            ctx.shadowBlur = status.icon_shadowblur;
            ctx.shadowColor = status.icon_shadowcolor;
            ctx.shadowOffsetX = status.icon_shadowoffsetx;
            ctx.shadowOffsetY = status.icon_shadowoffsety;
            ctx.fillStyle = status.icon_color;
            ctx.textAlign = status.icon_text_align;
            ctx.textBaseline = status.icon_base_line;
            ctx.font = status.icon_size + 'px ' + curIcon.iconType;
            ctx.fillText(curIcon.font, status.icon_x, parseInt(status.icon_y) + config.icon.height * i);

        };

        var draw = function () {
            for (var i = 0; i < config.icon.layer_count; i++) {
                (function (idx) {
                    initDrawGlobalIcon(idx);
                    initDrawStatus(status[idx], idx);
                })(i);
            }
        };

        var addSprite = function (cvs) {
            var imgData=ctx.getImageData(0,0,canvas.width,canvas.height);
            var image = {
                canvas:angular.copy(canvas),
                imgData : imgData
            };
            canvasQueue.push(image);
            drawSprite(cvs);
        };

        var removeSprite = function () {
            return canvasQueue.shift();
        };

        var removeSpriteByIdx = function (idx,cvs) {
            canvasQueue.splice(idx,1);
            drawSprite(cvs);
        };


        var drawSprite = function(cvs){
            var cvsWidth = 0;
            var cvsHeight = 0;
            canvasQueue.forEach(function (val) {
                cvsWidth+=val.canvas.width;
                if(cvsHeight < val.canvas.height){
                    cvsHeight = val.canvas.height;
                }
            });
            cvs.width = cvsWidth;
            cvs.height = cvsHeight;
            var ctx = cvs.getContext('2d');
            var imgX = 0;
            ctx.clearRect(0,0,cvsWidth,cvsHeight);

            canvasQueue.forEach(function (val) {
                ctx.putImageData(val.imgData, imgX, 0);
                imgX+=val.canvas.width;
            });

        };
        var canvas2Img = function (cvs) {
            var type = "image/"+configService.config.icon.filename_prefix;
            return cvs.toDataURL(type).replace(type, 'image/octet-stream');

        };

        var drawColorPanel = function (option) {

            var panelCtx = option.panelCvs.getContext('2d');
            panelCtx.clearRect(0,0,option.panelCvs.width,option.panelCvs.height);

            var everySaturation = 100 / option.panelCvs.height;
            var saturation = option.saturation;
            var hue= option.hue;
            var value,sl;
            var ConstValue = 100;
            var alpha = option.alpha;

            for(var i = 0; i < option.panelCvs.height; i ++){
                value = ConstValue - everySaturation * i;
                sl = value / 480;
                for(var j = 0; j < option.panelCvs.width; j ++){
                    panelCtx.fillStyle = 'hsla(' + hue + ',' + parseInt(saturation) + '%,' + parseInt(value) + '%,'+alpha+')';
                    panelCtx.fillRect(j, i, 1, 1);
                    value -= sl;
                }
                saturation -= everySaturation;
            }

            // directionCvs
            var directionCtx = option.directionCvs.getContext('2d');
            directionCtx.clearRect(0,0,option.directionCvs.width,option.directionCvs.height);
            for(i = 0; i <= 180; i ++){
                directionCtx.fillStyle = 'hsl(' + (360 - i * 2) + ',100%,50%)';
                directionCtx.fillRect(0, i, 16, 1);
            }

            var alphaCtx = option.alphaCvs.getContext('2d');
            alphaCtx.clearRect(0,0,option.alphaCvs.width,option.alphaCvs.height);
            alphaCtx.fillStyle = 'rgba(0,0,0,0.3)';
            var _halfH = Math.floor(option.alphaCvs.width / 2), _gridCnt = Math.floor(option.alphaCvs.height / _halfH);
            var x = 0;
            var y = 0;
            for (i = 0; i < _gridCnt; i += 2) {
                alphaCtx.fillRect(y, x + i * _halfH, _halfH, _halfH);
                alphaCtx.fillRect(y + _halfH, x + (i + 1) * _halfH, _halfH, _halfH);
            }


            var linearColor = alphaCtx.createLinearGradient(option.alphaCvs.width,option.alphaCvs.height,x,y);
            linearColor.addColorStop(0,'hsla('+hue+',100%,50%,0)');
            linearColor.addColorStop(1,'hsla('+hue+',100%,50%,1)');

            alphaCtx.fillStyle = linearColor;
            alphaCtx.strokeStyle = '#000000';
            alphaCtx.fillRect(x,y,option.alphaCvs.width,option.alphaCvs.height);
            alphaCtx.strokeRect(x,y,option.alphaCvs.width,option.alphaCvs.height);
        };


        return {
            initCanvas: initCanvas,
            draw: draw,
            addSprite: addSprite,
            removeSprite: removeSprite,
            removeSpriteByIdx:removeSpriteByIdx,
            canvasQueue:canvasQueue,
            canvas2Img:canvas2Img,
            drawColorPanel:drawColorPanel
        }
    }]);