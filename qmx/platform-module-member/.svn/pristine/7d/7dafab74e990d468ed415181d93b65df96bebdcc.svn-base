// Zepto.js
// (c) 2010-2014 Thomas Fuchs
// Zepto.js may be freely distributed under the MIT license.
/*;(function($){
var touch = {},
touchTimeout, tapTimeout, swipeTimeout, longTapTimeout,
longTapDelay = 750,
gesture
function swipeDirection(x1, x2, y1, y2) {
return Math.abs(x1 - x2) >=
Math.abs(y1 - y2) ? (x1 - x2 > 0 ? 'Left' : 'Right') : (y1 - y2 > 0 ? 'Up' : 'Down')
}
function longTap() {
longTapTimeout = null
if (touch.last) {
touch.el.trigger('longTap')
touch = {}
}
}
function cancelLongTap() {
if (longTapTimeout) clearTimeout(longTapTimeout)
longTapTimeout = null
}
function cancelAll() {
if (touchTimeout) clearTimeout(touchTimeout)
if (tapTimeout) clearTimeout(tapTimeout)
if (swipeTimeout) clearTimeout(swipeTimeout)
if (longTapTimeout) clearTimeout(longTapTimeout)
touchTimeout = tapTimeout = swipeTimeout = longTapTimeout = null
touch = {}
}
function isPrimaryTouch(event){
return (event.pointerType == 'touch' ||
event.pointerType == event.MSPOINTER_TYPE_TOUCH)
&& event.isPrimary
}
function isPointerEventType(e, type){
return (e.type == 'pointer'+type ||
e.type.toLowerCase() == 'mspointer'+type)
}
$(document).ready(function(){
var now, delta, deltaX = 0, deltaY = 0, firstTouch, _isPointerType
if ('MSGesture' in window) {
gesture = new MSGesture()
gesture.target = document.body
}
$(document)
.bind('MSGestureEnd', function(e){
var swipeDirectionFromVelocity =
e.velocityX > 1 ? 'Right' : e.velocityX < -1 ? 'Left' : e.velocityY > 1 ? 'Down' : e.velocityY < -1 ? 'Up' : null;
if (swipeDirectionFromVelocity) {
touch.el.trigger('swipe')
touch.el.trigger('swipe'+ swipeDirectionFromVelocity)
}
})
.on('touchstart MSPointerDown pointerdown', function(e){
if((_isPointerType = isPointerEventType(e, 'down')) &&
!isPrimaryTouch(e)) return
firstTouch = _isPointerType ? e : e.touches[0]
if (e.touches && e.touches.length === 1 && touch.x2) {
// Clear out touch movement data if we have it sticking around
// This can occur if touchcancel doesn't fire due to preventDefault, etc.
touch.x2 = undefined
touch.y2 = undefined
}
now = Date.now()
delta = now - (touch.last || now)
touch.el = $('tagName' in firstTouch.target ?
firstTouch.target : firstTouch.target.parentNode)
touchTimeout && clearTimeout(touchTimeout)
touch.x1 = firstTouch.pageX
touch.y1 = firstTouch.pageY
if (delta > 0 && delta <= 250) touch.isDoubleTap = true
touch.last = now
longTapTimeout = setTimeout(longTap, longTapDelay)
// adds the current touch contact for IE gesture recognition
if (gesture && _isPointerType) gesture.addPointer(e.pointerId);
})
.on('touchmove MSPointerMove pointermove', function(e){
if((_isPointerType = isPointerEventType(e, 'move')) &&
!isPrimaryTouch(e)) return
firstTouch = _isPointerType ? e : e.touches[0]
cancelLongTap()
touch.x2 = firstTouch.pageX
touch.y2 = firstTouch.pageY
deltaX += Math.abs(touch.x1 - touch.x2)
deltaY += Math.abs(touch.y1 - touch.y2)
})
.on('touchend MSPointerUp pointerup', function(e){
if((_isPointerType = isPointerEventType(e, 'up')) &&
!isPrimaryTouch(e)) return
cancelLongTap()
// swipe
if ((touch.x2 && Math.abs(touch.x1 - touch.x2) > 30) ||
(touch.y2 && Math.abs(touch.y1 - touch.y2) > 30))
swipeTimeout = setTimeout(function() {
touch.el.trigger('swipe')
touch.el.trigger('swipe' + (swipeDirection(touch.x1, touch.x2, touch.y1, touch.y2)))
touch = {}
}, 0)
// normal tap
else if ('last' in touch)
// don't fire tap when delta position changed by more than 30 pixels,
// for instance when moving to a point and back to origin
if (deltaX < 30 && deltaY < 30) {
// delay by one tick so we can cancel the 'tap' event if 'scroll' fires
// ('tap' fires before 'scroll')
tapTimeout = setTimeout(function() {
// trigger universal 'tap' with the option to cancelTouch()
// (cancelTouch cancels processing of single vs double taps for faster 'tap' response)
var event = $.Event('tap')
event.cancelTouch = cancelAll
touch.el.trigger(event)
// trigger double tap immediately
if (touch.isDoubleTap) {
if (touch.el) touch.el.trigger('doubleTap')
touch = {}
}
// trigger single tap after 250ms of inactivity
else {
touchTimeout = setTimeout(function(){
touchTimeout = null
if (touch.el) touch.el.trigger('singleTap')
touch = {}
}, 250)
}
}, 0)
} else {
touch = {}
}
deltaX = deltaY = 0
})
// when the browser window loses focus,
// for example when a modal dialog is shown,
// cancel all ongoing events
.on('touchcancel MSPointerCancel pointercancel', cancelAll)
// scrolling the window indicates intention of the user
// to scroll, not tap or swipe, so cancel all ongoing events
$(window).on('scroll', cancelAll)
})
;['swipe', 'swipeLeft', 'swipeRight', 'swipeUp', 'swipeDown',
'doubleTap', 'tap', 'singleTap', 'longTap'].forEach(function(eventName){
$.fn[eventName] = function(callback){ return this.on(eventName, callback) }
})
})(Zepto)*/
(function($){

 

$.fn.touchEventBind = function(touch_options)

{

var touchSettings = $.extend({

tapDurationThreshold : 250,//触屏大于这个时间不当作tap

scrollSupressionThreshold : 10,//触发touchmove的敏感度

swipeDurationThreshold : 750,//大于这个时间不当作swipe

horizontalDistanceThreshold: 30,//swipe的触发垂直方向move必须小于这个距离

verticalDistanceThreshold: 75,//swipe的触发水平方向move必须大于这个距离

tapHoldDurationThreshold: 750,//swipe的触发水平方向move必须大于这个距离

doubleTapInterval: 250//swipe的触发水平方向move必须大于这个距离

}, touch_options || {})

var touch = {}, touchTimeout ,delta ,longTapTimeout;



function parentIfText(node){

return 'tagName' in node ? node : node.parentNode

}



function swipeDirection(x1, x2, y1, y2){

var xDelta = Math.abs(x1 - x2), yDelta = Math.abs(y1 - y2)

return xDelta >= yDelta ? (x1 - x2 > 0 ? 'Left' : 'Right') : (y1 - y2 > 0 ? 'Up' : 'Down')

}



function longTap()

{

longTapTimeout = null

touch.el.trigger('longTap');

touch.longTap = true;

touch = {};

}



function cancelLongTap()

{

if (longTapTimeout) clearTimeout(longTapTimeout)

longTapTimeout = null

}





this.data('touch_event_bind',"true");

this.bind('touchstart', function(e)

{

touchTimeout && clearTimeout(touchTimeout);



touch.el = $(parentIfText(e.touches[0].target));

now = Date.now();

delta = now - (touch.last_touch_time || now);

touch.x1 = e.touches[0].pageX;

touch.y1 = e.touches[0].pageY;

touch.touch_start_time = now;

touch.last_touch_time = now;

if (delta > 0 && delta <= touchSettings.doubleTapInterval) touch.isDoubleTap = true;



longTapTimeout = setTimeout(function(){



longTap();

},touchSettings.tapHoldDurationThreshold);



}).bind('touchmove',function(e)

{

cancelLongTap();



touch.x2 = e.touches[0].pageX;

touch.y2 = e.touches[0].pageY;

// prevent scrolling

if ( Math.abs( touch.x1 - touch.x2 ) > touchSettings.scrollSupressionThreshold )

{

e.preventDefault();

}

touch.touch_have_moved = true;





}).bind('touchend',function(e)

{

cancelLongTap();



now = Date.now();

touch_duration = now - (touch.touch_start_time || now);

if(touch.isDoubleTap)

{

touch.el.trigger('doubleTap');

touch = {};

}

else if(!touch.touch_have_moved && touch_duration < touchSettings.tapDurationThreshold)

{

touch.el.trigger('tap');



touchTimeout = setTimeout(function(){

touchTimeout = null;

touch.el.trigger('singleTap');

touch = {};

}, touchSettings.doubleTapInterval);

}

else if ( touch.x1 && touch.x2 )

{

if ( touch_duration < touchSettings.swipeDurationThreshold && Math.abs( touch.x1 - touch.x2 ) > touchSettings.verticalDistanceThreshold && Math.abs( touch.y1 - touch.y2 ) < touchSettings.horizontalDistanceThreshold )

{

touch.el.trigger('swipe').trigger( touch.x1 > touch.x2 ? "swipeLeft" : "swipeRight" );

touch = {};

}

}

}).bind('touchcancel',function(e){

touchTimeout && clearTimeout(touchTimeout);

cancelLongTap();

touch = {};

})

}



$.fn.touchbind = function(m,callback,touch_options)

{

if(this.data('touch_event_bind')!="true")

{

this.touchEventBind(touch_options);

}

this.bind(m,callback);

}



 ;['swipe', 'swipeLeft', 'swipeRight', 'swipeUp', 'swipeDown', 'doubleTap', 'tap', 'singleTap', 'longTap'].forEach(function(m)

 {

 $.fn[m] = function(touch_options,callback)

 {

 if(typeof(touch_options)=="object" && typeof(callback)=="function")

 {

 return this.touchbind(m, callback , touch_options)

 }

 else if(typeof(touch_options)=="function")

 {

 var callback = touch_options;

 return this.touchbind(m, callback)

 }

 }

 })

})(Zepto)