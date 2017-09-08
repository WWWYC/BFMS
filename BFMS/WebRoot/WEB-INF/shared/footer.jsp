<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="text" hidden="hidden" id="callBack" style="display: none;"/>
<div class="modal" tabindex="-1" role="dialog" id="yesorno">
  <div class="modal-dialog modal-sm" role="document" style="width:435px;">
    <div class="modal-content">
      <div class="modal-body"id="yesornoContent">
         ...
      </div>
      <div class="modal-footer">
		    <button type="button" class="btn btn-default" data-dismiss="modal" id="yes">确定</button>
		    <button type="button" class="btn btn-primary" id="no">取消</button>
		  </div>
    </div>
  </div>
</div>
<div class="modal" tabindex="-1" role="dialog" id="okmodal">
  <div class="modal-dialog modal-sm" role="document" style="width:435px;">
    <div class="modal-content">
      <div class="modal-body"id="okmodalContent">
         ...
      </div>
      <div class="modal-footer">
		    <button type="button" class="btn btn-default" data-dismiss="modal" id="ok">确定</button>
		  </div>
    </div>
  </div>
</div>
