<template>

</template>

<script>
    export default {
        name: "Error",
        
        created() {
            let _this = this
            this.$alert('登录信息失效！', '提示', {
                confirmButtonText: '确定'
            }).then((response) => {
                localStorage.removeItem('User')
                _this.$router.replace({path: '/'})
            }).catch(() => {
          			 _this.$router.replace({path: '/'})         
        		});
        }
    }
</script>

<style scoped>

</style>
