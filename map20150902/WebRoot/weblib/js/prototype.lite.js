/*  Prototype JavaScript framework
 *  (c) 2005 Sam Stephenson <sam@conio.net>
 *  Prototype is freely distributable under the terms of an MIT-style license.
 *  For details, see the Prototype web site: http://prototype.conio.net/
/*--------------------------------------------------------------------------*/

//note: modified & stripped down version of prototype, to be used with moo.fx by mad4milk (http://moofx.mad4milk.net).

var Class = {
	create: function() {
		return function() {
			this.initialize.apply(this, arguments);
		}
	}
}

Object.extend = function(destination, source) {
	for (property in source) destination[property] = source[property];
	return destination;
}

Function.prototype.bind = function(object) {
	var __method = this;
	return function() {
		return __method.apply(object, arguments);
	}
}

Function.prototype.bindAsEventListener = function(object) {
var __method = this;
	return function(event) {
		__method.call(object, event || window.event);
	}
}

function f$() {
	if (arguments.length == 1) return getf$(arguments[0]);
	var elements = [];
	f$c(arguments).each(function(el){
		elements.push(getf$(el));
	});
	return elements;

	function getf$(el){
		if (typeof el == 'string') el = document.getElementById(el);
		return el;
	}
}

if (!window.Element) var Element = new Object();

Object.extend(Element, {
	remove: function(element) {
		element = f$(element);
		element.parentNode.removeChild(element);
	},

	hasClassName: function(element, className) {
		element = f$(element);
		if (!element) return;
		var hasClass = false;
		element.className.split(' ').each(function(cn){
			if (cn == className) hasClass = true;
		});
		return hasClass;
	},

	addClassName: function(element, className) {
		element = f$(element);
		Element.removeClassName(element, className);
		element.className += ' ' + className;
	},
  
	removeClassName: function(element, className) {
		element = f$(element);
		if (!element) return;
		var newClassName = '';
		element.className.split(' ').each(function(cn, i){
			if (cn != className){
				if (i > 0) newClassName += ' ';
				newClassName += cn;
			}
		});
		element.className = newClassName;
	},

	cleanWhitespace: function(element) {
		element = f$(element);
		f$c(element.childNodes).each(function(node){
			if (node.nodeType == 3 && !/\S/.test(node.nodeValue)) Element.remove(node);
		});
	},

	find: function(element, what) {
		element = f$(element)[what];
		while (element.nodeType != 1) element = element[what];
		return element;
	}
});

var Position = {
	cumulativeOffset: function(element) {
		var valueT = 0, valueL = 0;
		do {
			valueT += element.offsetTop  || 0;
			valueL += element.offsetLeft || 0;
			element = element.offsetParent;
		} while (element);
		return [valueL, valueT];
	}
};

document.getElementsByClassName = function(className) {
	var children = document.getElementsByTagName('*') || document.all;
	var elements = [];
	f$c(children).each(function(child){
		if (Element.hasClassName(child, className)) elements.push(child);
	});  
	return elements;
}

//useful array functions
Array.prototype.iterate = function(func){
	for(var i=0;i<this.length;i++) func(this[i], i);
}
if (!Array.prototype.each) Array.prototype.each = Array.prototype.iterate;

function f$c(array){
	var nArray = [];
	for (var i=0;i<array.length;i++) nArray.push(array[i]);
	return nArray;
}