<template>
    <div class="displayUse" v-loading="loading">
        <el-table :data="list" >
           <el-table-column prop="teacherName" label="老师"></el-table-column>
　　　　　　<el-table-column prop="roomName" label="教室"></el-table-column>
　　　　　　<el-table-column prop="day" label="日期"></el-table-column>
　　　　　　<el-table-column prop="beginTime" label="开始时间"></el-table-column>
　　　　　　<el-table-column prop="endTime" label="结束时间" ></el-table-column>
　　　　　　<el-table-column prop="state" label="教室状态">
　　　　　　　　<template slot-scope="scope">
　　　　　　　　　　<span v-if="scope.row.state=== 0" style="color:#FF4040">未通过申请
                    <!--<el-button @click="agree(scope.row)" style="margin-left:20px" type="primary" icon="el-icon-edit" size="small" v-if="scope.row.state=== 0">通过</el-button>-->
                    </span>
　　　　　　　　　　<el-button @click="agree(scope.row)" type="primary" icon="el-icon-edit" size="small" 
                    v-if="scope.row.state=== 1">待通过</el-button>
                    <el-button @click="refuse(scope.row)" type="warning" icon="el-icon-edit" size="small" 
                    v-if="scope.row.state=== 1">驳回</el-button>
　　　　　　　　　　<span v-if="scope.row.state=== 2" style="color:#33cc00">已通过申请</span>
　　　　　　　　</template>
　　　　　　</el-table-column>
        </el-table>
	    </div>
    </div>
</template>
<script>
    import axios from 'axios'
    import qs from 'qs'
    export default {
        data(){
			this.user=JSON.parse(localStorage.getItem('User'))
            //console.log(this.user.name)
			return {
      			list:[],
                loading: false,
                id:'',
    		}
		},
        mounted:function(){
        	this.checkToken();
            this.getAll();
	  	},
		methods: {
        	checkToken:function(){
          	var _this = this;
          	axios({
            	url: 'http://localhost:8081/checkToken',
            	method: 'get',
            	headers: {
              		token: _this.user.token
          		}
        		}).then((response) => {
        			console.log(response.data)
        		if(!response.data){
          			console.log('校验失败')
          			_this.$router.push({path:'/error'})
        		}
      			})
        	},
            getAll:function(){
                var _this = this;
                _this.loading = true;
          	    axios({
            	    url: 'http://localhost:8081/displayAll',
            	    method: 'get',
        		}).then((response) => {
                    _this.loading = false;
        		    _this.list=response.data.data.classUse;
                    console.log(response.data.data.classUse)
      			})
            },
            agree:function(row){
                 var _this = this;
                 //console.log(row.id)
                _this.$confirm('确认通过?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                //let data=[row.id,this.user.name]
                var data={id:row.id,name:_this.user.name}
                console.log(data)
                axios.put("http://localhost:8081/agreeById",qs.stringify({
                    id: row.id,
                    name: _this.user.name,
                }))
                .then(resp=>{
          		         _this.loading = false;
				        if (resp.data.code == 200) {
					            _this.$message({type: 'success', message: '已通过!'
                                ,onClose:()=>{location.reload();}});
				        }else if(resp.data.code == 100){
                            _this.$message({
                                type: 'error',
                                message: '未通过',
                            })
                        }
			    }).catch(() => {
                    this.$message({
                        type: 'warning',
                        message: '抱歉！你没有权限'
                    }); 
                });
                        
                });
            },
            refuse:function(row){
                var _this = this;
                _this.$confirm('确认驳回?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.put("http://localhost:8081/refuseById",qs.stringify({
                    id: row.id,
                    name: _this.user.name,
                }))
                .then(resp=>{
          		         _this.loading = false;
				        if (resp.data.code == 200) {
					            _this.$message({type: 'success', message: '已驳回!',onClose:()=>{location.reload();}
                            });
                                
				        }else if(resp.data.code == 100){
                            _this.$message({
                                type: 'error',
                                message: '未成功驳回',
                            })
                        }
			    }).catch(() => {
                    this.$message({
                        type: 'warning',
                        message: '抱歉！你没有权限'
                    }); 
                });
                        
                });
            },
      
    },
    
    };
</script>
<style>
	
</style>
