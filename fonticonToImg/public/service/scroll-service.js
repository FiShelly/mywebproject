/**
 * Created by ddxfc on 2016/12/30.
 */
angular.module('dm.try.service.scroll', [])
    .factory('scrollService', [function () {
        var srollQueue = [];

        var add2Queue = function (obj) {
            srollQueue.push(obj);
            initMouseEvent(obj);
        };

        var remove2Queue = function () {
            srollQueue.shift();
        };

        var initMouseEvent = function (obj) {
            var isMove = false;
            var icad = obj.scrollElement;
            var limitHeight = obj.limitHeight || 0;
            var limitWidth = obj.limitWidth || 0;

            var scrollHeight = icad.offsetTop;
            var scrollWidth = icad.offsetLeft;
            var scrollCover = obj.cover;
            var parent = obj.parent;
            var body = document.querySelector('body');

            var canvas = obj.canvas;

            if (obj.type) {
                canvas.addEventListener('click', function (e) {
                    icad.style.top = (e.offsetY + scrollHeight) + 'px';
                    icad.style.left = (e.offsetX + scrollWidth) + 'px';
                    var lastTop = icad.offsetTop - scrollHeight;
                    var lastLeft = icad.offsetLeft - scrollWidth;
                    obj.callback(lastTop, lastLeft);
                });
            } else   {
                canvas.addEventListener('click', function (e) {
                    icad.style.top = (e.offsetY + scrollHeight) + 'px';
                    var lastTop = icad.offsetTop - scrollHeight;
                    var lastLeft = icad.offsetLeft - scrollWidth;
                    obj.callback(lastTop, lastLeft);
                });
            }

            icad.addEventListener('mousedown', function () {
                scrollCover.style.display = 'block';
                isMove = true;
            });
            icad.addEventListener('mouseup', function () {
                isMove = false;
            });
            body.addEventListener('mousemove', function (e) {
                var dis = e.clientY - parent.offsetTop;
                var left = e.clientX - parent.offsetLeft;

                if (isMove && (dis <= scrollHeight)) {
                    dis = scrollHeight;
                } else if (isMove && (dis > limitHeight + scrollHeight)) {
                    dis = icad.offsetTop - icad.height;
                }

                if (isMove && (left <= scrollWidth)) {
                    left = scrollWidth
                } else if (isMove && (left > limitWidth + scrollWidth)) {
                    left = icad.offsetLeft - icad.width;
                }

                if (isMove) {
                    icad.style.top = dis + 'px';
                    icad.style.left = left + 'px';

                    var lastTop = icad.offsetTop - scrollHeight;
                    var lastLeft = icad.offsetLeft - scrollWidth;
                    obj.callback(lastTop, lastLeft);
                }
            });
            body.addEventListener('mouseup', function () {
                if (isMove) {
                    var lastTop = icad.offsetTop - scrollHeight;
                    var lastLeft = icad.offsetLeft - scrollWidth;
                    obj.callback(lastTop, lastLeft);
                    isMove = false;
                }
            });
        };

        var scrollUtil = function () {

            var leftListContentBody = null;
            var leftListScrollBar = null;
            var leftListScrollThumb = null;
            var leftListContentFrame = null;
            var body = null;

            var contentBodyHeight = null;
            var scrollBarHeight = null;
            var scrollThumbHeight = null;
            var contentFrameHeight = null;
            var thumbSubBar = null;
            var bodySubFrame = null;
            var wheelStep = null;
            var bs = null;
            var scrollHeight = 0;
            var isMove = false;
            var dis1 = 0;
            var disRes = 0;
            var scrollCover = null;

            var initElement = function (boddy, bar, thumb, frame) {
                leftListContentBody = document.querySelector(boddy);
                leftListScrollBar = document.querySelector(bar);
                leftListScrollThumb = document.querySelector(thumb);
                leftListContentFrame = document.querySelector(frame);
                body = document.querySelector('body');
                scrollCover = document.querySelector('#scroll-cover');
            };

            var initAllKindOfHeight = function () {
                contentBodyHeight = leftListContentBody.offsetHeight;
                scrollBarHeight = leftListScrollBar.offsetHeight;
                scrollThumbHeight = leftListScrollThumb.offsetHeight;
                contentFrameHeight = leftListContentFrame.offsetHeight;
                //判断内容高度是否小于容器高度
                if (contentFrameHeight >= contentBodyHeight) {
                    scrollBarHeight = scrollThumbHeight;
                } else {
                    scrollBarHeight = contentFrameHeight / contentBodyHeight * scrollThumbHeight;
                }

                leftListScrollBar.style.height = scrollBarHeight + 'px';

                thumbSubBar = scrollThumbHeight - scrollBarHeight;
                bodySubFrame = contentBodyHeight - contentFrameHeight;
                wheelStep = contentFrameHeight / (bodySubFrame) / 2 * thumbSubBar;
                bs = contentBodyHeight / scrollThumbHeight;
            };

            var initEventListener = function () {
                leftListScrollThumb.addEventListener('mousewheel', function (e) {
                    scrollEvent(e.deltaY, wheelStep);
                });
                leftListContentFrame.addEventListener('mousewheel', function (e) {
                    scrollEvent(e.deltaY, wheelStep);
                });
                leftListScrollBar.addEventListener('mousedown', function (e) {
                    isMove = true;
                    scrollCover.style.display = 'block';
                    dis1 = e.clientY;
                    e.cancelBubble = true;
                    e.stopPropagation();
                });
                body.addEventListener('mouseup', function (e) {
                    scrollCover.style.display = 'none';
                    isMove = false;
                    dis1 = e.clientY;
                });
                body.addEventListener('mousemove', function (e) {
                    if (isMove) {
                        disRes = e.clientY - dis1;
                        dis1 = e.clientY;
                        scrollEvent(disRes, Math.abs(disRes))
                    }
                });

            };
            var scrollEvent = function (res, distance) {
                var scrollBoxHeight = scrollThumbHeight;

                scrollBoxHeight -= scrollBarHeight;
                if (res >= 0) {
                    scrollHeight += distance;
                } else {
                    scrollHeight -= distance;
                }
                if (scrollHeight <= 0) {
                    scrollHeight = 0;
                } else if (scrollHeight >= scrollBoxHeight) {
                    scrollHeight = scrollBoxHeight;
                }
                leftListScrollBar.style.transform = 'translateY(' + scrollHeight + 'px)';
                var temp = scrollHeight * -bs;

                leftListContentBody.style.transform = 'translateY(' + temp + 'px)';
            };


            var jumpScroll = function (event) {
                scrollHeight = event.offsetY;

                if (scrollHeight + scrollBarHeight >= scrollThumbHeight) {
                    scrollHeight = scrollThumbHeight - scrollBarHeight;
                }

                leftListScrollBar.style.transform = 'translateY(' + scrollHeight + 'px)';
                leftListContentBody.style.transform = 'translateY(' + (scrollHeight * -bs) + 'px)';
                event.cancelBubble = true;
                event.stopPropagation();
            };

            var init = function (bbody, bar, thumb, frame) {
                initElement(bbody, bar, thumb, frame);
                initAllKindOfHeight();
                initEventListener();
            };
            var initElementUseDoc = function (option) {
                leftListContentBody = option.bbody;
                leftListScrollBar = option.bar;
                leftListScrollThumb = option.thumb;
                leftListContentFrame = option.frame;
                body = document.querySelector('body');
                scrollCover = document.querySelector('#scroll-cover');
            };

            var initUseDoc = function (option) {
                initElementUseDoc(option);
                initAllKindOfHeight();
                initEventListener();
            };
            return {
                init: init,
                initUseDoc: initUseDoc,
                jumpScroll: jumpScroll
            }
        };

        return {
            add2Queue: add2Queue,
            remove2Queue: remove2Queue,
            scrollUtil: scrollUtil
        }
    }]);