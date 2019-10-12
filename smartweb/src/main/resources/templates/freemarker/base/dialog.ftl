

<div class="modal fade" id="${modalId}" style="display: none;" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">提示</h5>
                <button type="button" class="close" data-dismiss="modal"><span>×</span>
                </button>
            </div>
            <div class="modal-body">${moduleTitle}</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"
                        onclick="${moduleClick}">确认
                </button>
            </div>
        </div>
    </div>
</div>