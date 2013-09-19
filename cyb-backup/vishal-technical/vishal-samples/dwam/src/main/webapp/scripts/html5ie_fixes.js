$(function() {
     if(!$.support.placeholder) {
          var active = document.activeElement;
          $('textarea').each(function(index, element) {
           if($(this).val().length == 0) {
               $(this).html($(this).attr('id')).addClass('hasPlaceholder');
               }
        });
          $('input, textarea').focus(function () {
               if ($(this).attr('placeholder') != '' && $(this).val() == $(this).attr('placeholder')) {
                    $(this).val('').removeClass('hasPlaceholder');
               }
          }).blur(function () {
               if (($(this).attr('placeholder') != '' && ($(this).val() == '' || $(this).val() == $(this).attr('placeholder')))) {
                    $(this).val($(this).attr('placeholder')).addClass('hasPlaceholder');
                    //$(this).css('background', 'red');
               }
          });
          $(':text').blur();
          $(active).focus();
          $('form').submit(function () {
               $(this).find('.hasPlaceholder').each(function() { $(this).val(''); });
          });
     }
});