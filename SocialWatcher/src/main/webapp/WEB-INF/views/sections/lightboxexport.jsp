    <div id="lightboxexport" class="modal hide fade dark" role="dialog" aria-labelledby="bgModalUpdate" aria-hidden="true">
      <header class="modal-header">
        <a href="#" class="close" data-dismiss="modal">x</a>
        <h3>Choose export options</h3>
      </header>
        <div class="modal-body">
      <ul>
  		<li  style="list-style:none;">      
      <input type="radio" id="csv" name="export" checked="checked">
      <label for="csv"><span></span>CSV</label>

      <input type="radio" id="excel" name="export">
      <label for="excel"><span></span>EXCEL</label>
      
      <input type="radio" id="pdf" name="export">
      <label for="pdf"><span></span>PDF</label>
      </li>
	</ul>
<h5>Report Fields</h5>
      <ul>
  		<li  style="list-style:none;">  
  			<div class="span2">
				<input type="checkbox" id="name" name="reportFields" value="name"/>
				<label for="name" class="checkbox inline"><span></span>Name</label>
				<input type="checkbox" id="description" name="reportFields" value="description"/>
				<label for="description" class="checkbox inline"><span></span>Description</label>
				<input type="checkbox" id="url" name="reportFields" value="url"/>
				<label for="url" class="checkbox inline"><span></span>Url</label>
			</div>
			<div class="span2">
				<input type="checkbox" id="profileImage" name="reportFields" value="profileImage"/>
				<label for="profileImage" class="checkbox inline"><span></span>Profile Image</label>
				<input type="checkbox" id="isReviewed" name="reportFields" value="isReviewed"/>
				<label for="isReviewed" class="checkbox inline"><span></span>Is Reviewed</label>
				<input type="checkbox" id="createdBy" name="reportFields" value="createdBy"/>
				<label for="createdBy" class="checkbox inline"><span></span>Created By</label>
			</div>
			<div class="span2">
				<input type="checkbox" id="createdDate" name="reportFields" value="createdDate"/>
				<label for="createdDate" class="checkbox inline"><span></span>Created Date</label>
				<input type="checkbox" id="comment" name="reportFields" value="comment"/>
				<label for="comment" class="checkbox inline"><span></span>Comment</label>
				<input type="checkbox" id="sourceId" name="reportFields" value="sourceId"/>
				<label for="sourceId" class="checkbox inline"><span></span>Source Id</label>
			</div>
			<div class="span2">
				<input type="checkbox" id="sourceCreatedDate" name="reportFields" value="sourceCreatedDate"/>
				<label for="sourceCreatedDate" class="checkbox inline"><span></span>Tweet Date</label>
				<input type="checkbox" id="isDeleted" name="reportFields" value="isDeleted"/>
				<label for="isDeleted" class="checkbox inline"><span></span>Is Deleted</label>
				<input type="checkbox" id="place" name="reportFields" value="place"/>
				<label for="place" class="checkbox inline"><span></span>Place</label>
			</div>
	  </li>
	</ul>
	</div>
      <footer class="modal-footer">
        <a href="#" class="btn btn-small" id="closewin">Cancel</a>
        <a href="#" class="btn btn-small btn-primary" id="export">Export</a>
      </footer> 
    </div>  