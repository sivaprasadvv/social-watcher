function onNext(parent, panel) {
  
  $(".acc-wizard-todo").click(function(e) {
    e.preventDefault();
  });
  
  hash = "#" + panel.id;
  $(".acc-wizard-sidebar",$(parent))
  .children("li")
  .children("a[href='" + hash + "']")
  .parent("li")
  .removeClass("acc-wizard-todo")
  .addClass("acc-wizard-completed");
}

$.getScript(application_context+'/resources/js/bootstrap-accordian/acc-wizard.min.js',function(){
  $(".acc-wizard").accwizard({
    onNext: onNext
  });
      
});