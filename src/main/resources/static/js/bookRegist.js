


function execute(){
	console.log("execute");
	var titleNode = document.querySelector('#title');
	var stockNode = document.querySelector('#stock');
	console.log(titleNode.value);
	
	var flg = true;
	var msg = '';
	
	if(!titleNode.value || !stockNode.value) {
		flg = false
		msg = '必須項目を入力して下さい';
	}
	
	if(stockNode.value < 1) {
		flg = false
		msg = '在庫が不正な値です';
	}
	
	if (flg){
		var result = confirm('登録処理を実行します');
		if (result) {
			document.registform.submit();
		}
	}else{
		alert(msg);
	}

}

function cancelClick(){
	history.back();
}