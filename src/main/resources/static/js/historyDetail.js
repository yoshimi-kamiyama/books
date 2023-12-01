/**
 * ユーザ詳細ページ
 */

 
 // 削除機能
 async function deleteClick(historyId){
	 
	 console.log(historyId);
	 
	 var result = confirm('本当に削除しますか？');
	 
	if (result) {
		const url = "http://localhost:8080/history/delete";
		const form = new FormData();
		form.append('id', historyId);
		const params = {
			method : "POST",
			headers: {
				'X-CSRF-Token': getCsrfToken()
			},
			body : form
		};
		
		var res = await fetch(url, params);

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
	var param = document.querySelector('input[name="dueDate"]');
//	console.log(new Date(param.value));
//	console.log(new Date());
	var nowDate = new Date();
	var updateDate = new Date(param.value);
	
	// パラメータと現在日時を比較してバリデーション
	if(nowDate > updateDate){
		alert('無効な日付です\n明日以降の日付を入力して下さい');
	}else{
		var result = confirm('本当に更新しますか？');
		if (result) {
			document.updateform.submit();
		}
	}
 }
 
 async function returnBook(historyId){
	
	var status = document.querySelector('#status')
	
	if (status.textContent == 'T') {
		alert('既に返却されています');	
	}else{
		var result = confirm('返却処理を実行します');
		if (result) {
			const url = "http://localhost:8080/history/return";
			const form = new FormData();
			form.append('id', historyId);
			const params = {
				method : "POST",
				headers: {
					'X-CSRF-Token': getCsrfToken()
				},
				body : form
			};
			
			var res = await fetch(url, params)
	
			if (res.status == 200){
				alert("返却処理が完了しました");	
			}else{
				alert("返却処理中に問題が発生しました");
			}
			window.location.assign(res.url);
		}		
	}	 
 }
 
 function getCsrfToken(){
	 // jsからfetchでリクエストを送る場合はcsrfトークンを手動で持ってくる必要がある
	var csrfElm = document.querySelector('input[name="_csrf"]');
	var csrf_token = csrfElm.value;
	return csrf_token;
 }