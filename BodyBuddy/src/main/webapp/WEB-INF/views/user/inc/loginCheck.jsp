<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(()=>{
		loginCheck();
	})

	function loginCheck() {
		<sec:authorize access="isAnonymous()">
			Swal.fire({
				title:"로그인해야 사용할 수 있는 기능입니다",
				icon:"warning",
				confirmButtonText:"확인",
				confirmButtonColor: '#c5f016'
			}).then(()=>{
				location.href="/";
			});
		</sec:authorize>
	}
</script>