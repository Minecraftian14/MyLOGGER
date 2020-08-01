
var Expand = (function() {
  var tile = $('.strips__strip');
  var tileLink = $('.strips__strip > .strip__content');
  var tileText = tileLink.find('.strip__inner-text');
  var stripClose = $('.strip__close');

  var expanded  = false;

  var open = function() {

    var tile = $(this).parent();

      if (!expanded) {
        tile.addClass('strips__strip--expanded');
        // add delay to inner text
        tileText.css('transition', 'all .5s .3s cubic-bezier(0.23, 1, 0.32, 1)');
        stripClose.addClass('strip__close--show');
        stripClose.css('transition', 'all .6s 1s cubic-bezier(0.23, 1, 0.32, 1)');
        expanded = true;
      }
    };

  var close = function() {
    if (expanded) {
      tile.removeClass('strips__strip--expanded');
      // remove delay from inner text
      tileText.css('transition', 'all 0.15s 0 cubic-bezier(0.23, 1, 0.32, 1)');
      stripClose.removeClass('strip__close--show');
      stripClose.css('transition', 'all 0.2s 0s cubic-bezier(0.23, 1, 0.32, 1)')
      expanded = false;
    }
  }

    var bindActions = function() {
      tileLink.on('click', open);
      stripClose.on('click', close);
    };

    var init = function() {
      bindActions();
    };

    return {
      init: init
    };

  }());

Expand.init();


new Vue({
    el: '#appFileLogger',
    data: {
        active: 0,
        animation: 'fadeUp',
        previous: 0,
        className: ''
    },
    methods: {
        selectedContent(index) {
            this.active = index;

            if(index > this.previous) {
                this.animation = 'fadeUp';
            }

            if(index < this.previous) {
                this.animation = 'fadeDown';
            }

            this.previous = index;

            if(index === 0) {
                this.className = 'FileLoggerA'
            }
            if(index === 1) {
                this.className = 'FileLoggerB'
            }
        }
    }
})