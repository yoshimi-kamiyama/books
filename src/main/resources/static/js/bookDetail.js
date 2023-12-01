/**
 * 書籍詳細ページ
 */

 
 // 削除機能
 async function deleteClick(bookId){
	 
	 console.log(bookId);
	 
	 var result = confirm('本当に削除しますか？');
	 
	if (result) {
		const url = "http://localhost:8080/book/delete";
		const form = new FormData();
		form.append('id', bookId);
		const params = {
			method : "POST", 
			headers: {
					'X-CSRF-Token': getCsrfToken()
			},
			body : form
		};
		
		var res = await fetch(url, params)

		console.log(res);
		if (res.status == 200){
			alert("削除しました");	
		}else{
			alert("削除処理中に問題が発生しました");
		}
		console.log(res.url);
		window.location.assign(res.url);
	}
 }
 
 function updateData(){
	var orgStockNode = document.querySelector('#org-stock');
	var stockNode = document.querySelector("input[name='stock']"); 
	var statusNode = document.querySelector("input[name='status']"); 
	var orgStock = orgStockNode.textContent;
	var stock = stockNode.value; 
	var updateFlg = false;
	
	if (stock < 0){
		alert('不正な値です');
	} else if (orgStock == 0 && stock > 0) {
	 // Not Available → Available
		statusNode.value = 1;
		updateFlg = true;
	} else if (orgStock > 0 && stock == 0){
		 // Available → Not Available
		statusNode.value = 2;
		updateFlg = true;
	}else　 if (orgStock == stock){
		alert('在庫数が変更されていません');
	}else{
		statusNode.value = 0;
		updateFlg = true;
	}

	console.log(orgStock);
	console.log(stock);
	console.log(statusNode.value);
	 
	if (updateFlg){
		var result = confirm('本当に更新しますか？');
		if (result) {
			document.updateform.submit();
		}	
	}

 }
 

function checkout(){
	var orgStockNode = document.querySelector('#org-stock');
	var orgStock = orgStockNode.textContent;
	console.log(`stock： ${orgStock}`);
	
	if (orgStock == 0){
		alert("在庫不足により貸出できません");
	}else{
		document.checkoutform.submit();
	}
	
}

  function getCsrfToken(){
	 // jsからfetchでリクエストを送る場合はcsrfトークンを手動で持ってくる必要がある
	var csrfElm = document.querySelector('input[name="_csrf"]');
	var csrf_token = csrfElm.value;
	return csrf_token;
 }