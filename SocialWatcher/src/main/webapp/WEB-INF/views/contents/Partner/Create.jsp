<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<div id="formsContent">
	<form id="partnerForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="social.watcher.management.partner.create.legend"/></legend>
	<div class="row-fluid">
	<div class="span4">
	<label class="control-label" for="partnerName"><fmt:message key="social.watcher.management.partner.column.partnerName"/><em>*</em></label>
	<div class="controls">
		<input type="text" id="partnerName" name="partnerName" value="" maxlength="100" placeholder="Partner Name">
		<span class="help-inline"></span>
	</div>
	</div>
	<div class="span4">
	<label class="control-label" for="title"><fmt:message key="social.watcher.management.partner.column.title"/><em>*</em></label>
	<div class="controls">
		<input type="text" id="title" name="title" value="" placeholder="Title">
		<span class="help-inline"></span>
	</div>
	</div>
	<div class="span4">
	<label class="control-label" for="description"><fmt:message key="social.watcher.management.partner.column.description"/><em>*</em></label>
	<div class="controls">
		<input type="text" id="description" name="description" value="" placeholder="Description">
		<span class="help-inline"></span>
	</div>
	</div>
	</div>
	<div class="row-fluid">
	<div class="span4">
	<label class="control-label" for="name"><fmt:message key="management.login.column.name"/><em>*</em></label>
	<div class="controls">
		<input type="text" id="name" name="name" placeholder="Email" value="" maxlength="100">
		<span class="help-inline"></span>
	</div>
	</div>
	<div class="span4">
	<label class="control-label" for="password"><fmt:message key="management.login.column.password"/><em>*</em></label>
	<div class="controls">
		<input type="password" id="password" name="password" maxlength="50" value=""  placeholder="Password">
		<span class="help-inline"></span>
	</div>
	</div>
	<div class="span4">
	<label class="control-label" for="password_confirm"><fmt:message key="management.login.column.passwordConfirmation"/><em>*</em></label>
	<div class="controls">
		<input type="password" id="password_confirm" name="password_confirm" maxlength="50" value=""  placeholder="Confirm Password">
		<span class="help-inline"></span>
	</div>
	</div>
	</div>
	<div class="row-fluid">
	<div class="span4">
	<label class="control-label" for="allowedClientsCount"><fmt:message key="social.watcher.management.partner.column.allowed.clients.count"/><em>*</em></label>
	<div class="controls">
		<input type="text" id="allowedClientsCount" name="allowedClientsCount" value="" placeholder="Allowed Clients Count">
		<span class="help-inline"></span>
	</div>
	</div>
	<div class="span4">
	<label class="control-label" for="isEnabled">Is enabled?</label>
	<div class="controls">
		<input type="checkbox" id="isEnabled" name="isEnabled"  checked>
		<span class="help-inline"></span>
	</div>
	</div>
	<div class="span4">
	</div>
	</div>
	<br><br>
	<div class="row-fluid">
     <div class="span12">
       <label>Address</label><hr>           
     </div>
   </div>
		
	<div class="row-fluid">
       <div class="span12">
         <div class="control-group">
           <label class="control-label" for="street1">Street 1</label>
           <div class="controls">
             <input type="text" class="input-xxlarge" id="street1" name="street1">
             <span class="help-inline"></span>
           </div>
         </div>     
       </div>
     </div>    
     <div class="row-fluid">
       <div class="span6 bgcolor">
         <div class="control-group">
           <label class="control-label" for="city">City</label>
           <div class="controls">
             <input type="text" id="city" name="city"> 
             <span class="help-inline"></span>
           </div>
         </div>   
       </div><!--/span-->
       <div class="span6 bgcolor">
         <div class="control-group">
           <label class="control-label" for="postCode">Post Code</label>
           <div class="controls">
             <input type="text" id="postCode" name="postCode"> 
             <span class="help-inline"></span>
           </div>
         </div>    
       </div>
     </div>                    
     <div class="row-fluid"> 
     <div class="span6">
         <div class="control-group">
           <label class="control-label" for="state">State</label>
           <div class="controls">             
				<select class="input-medium bfh-states" data-country="country" id="state"></select>
           </div>
         </div>   
       </div>           
       <div class="span6">
         <div class="control-group">
           <label class="control-label" for="country">Country</label>
           <div class="controls">
               <select id="country" class="input-medium bfh-countries" data-country="US"></select>
           </div>
         </div>  
       </div>       
     </div>                            
     </div>  
	<div class="form-actions">
		<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.create"/> <fmt:message key="social.watcher.management.partner"/></button>
		<button id="cancelButton" type="button" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></button>
	</div>		
	</form>
</div>