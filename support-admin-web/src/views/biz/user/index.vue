<template>
	<a-row>
		<a-col :span="5">
			<a-card class="cardImp" :bordered="false" :loading="cardLoading">
				<a-tree
					v-if="treeData.length > 0"
					v-model:expandedKeys="defaultExpandedKeys"
					:tree-data="treeData"
					:field-names="treeFieldNames"
					@select="treeSelect"
				>
				</a-tree>
				<a-empty v-else :image="Empty.PRESENTED_IMAGE_SIMPLE" />
			</a-card>
		</a-col>
		<a-col :span="19">
			<a-card :bordered="false" style="margin-bottom: 10px">
				<a-form ref="searchFormRef" name="advanced_search" class="ant-advanced-search-form" :model="searchFormState">
					<a-row :gutter="24">
						<a-col :span="8">
							<a-form-item name="searchKey" :label="$t('common.searchKey')">
								<a-input
									v-model:value="searchFormState.searchKey"
									:placeholder="$t('user.placeholderNameAndSearchKey')"
								/>
							</a-form-item>
						</a-col>
						<a-col :span="8">
							<a-form-item name="userStatus" :label="$t('user.userStatus')">
								<a-select v-model:value="searchFormState.userStatus" :placeholder="$t('user.placeholderUserStatus')">
									<a-select-option v-for="item in statusData" :key="item.value" :value="item.value">{{
										item.label
									}}</a-select-option>
								</a-select>
							</a-form-item>
						</a-col>
						<a-col :span="8">
							<a-button type="primary" @click="table.refresh(true)">
								<template #icon><SearchOutlined /></template>
								{{ $t('common.searchButton') }}
							</a-button>
							<a-button class="support-buttom-left" @click="reset">
								<template #icon><redo-outlined /></template>
								{{ $t('common.resetButton') }}
							</a-button>
						</a-col>
					</a-row>
				</a-form>
			</a-card>
			<a-card :bordered="false">
				<s-table
					ref="table"
					:columns="columns"
					:data="loadData"
					:expand-row-by-click="true"
					bordered
					:alert="options.alert.show"
					:tool-config="toolConfig"
					:row-key="(record) => record.id"
					:row-selection="options.rowSelection"
				>
					<template #operator class="table-operator">
						<a-space>
							<a-button
								type="primary"
								@click="form.onOpen(undefined, searchFormState.orgId)"
								v-if="hasPerm('bizUserAdd')"
							>
								<template #icon><plus-outlined /></template>
								<span>{{ $t('common.addButton') }}{{ $t('model.user') }}</span>
							</a-button>
							<a-button @click="exportBatchUserVerify" v-if="hasPerm('bizUserBatchExport')">
								<template #icon><export-outlined /></template>
								{{ $t('user.batchExportButton') }}
							</a-button>
							<xn-batch-delete
								v-if="hasPerm('bizUserBatchDelete')"
								:buttonName="$t('common.batchRemoveButton')"
								:selectedRowKeys="selectedRowKeys"
								@batchDelete="deleteBatchUser"
							/>
						</a-space>
					</template>
					<template #bodyCell="{ column, record }">
						<template v-if="column.dataIndex === 'avatar'">
							<a-avatar :src="record.avatar" style="margin-bottom: -5px; margin-top: -5px" />
						</template>
						<template v-if="column.dataIndex === 'gender'">
							{{ $TOOL.dictTypeData('GENDER', record.gender) }}
						</template>
						<template v-if="column.dataIndex === 'userStatus'">
							<a-switch
								:loading="loading"
								:checked="record.userStatus === 'ENABLE'"
								@change="editStatus(record)"
								v-if="hasPerm('bizUserUpdataStatus')"
							/>
							<span v-else>{{ $TOOL.dictTypeData('COMMON_STATUS', record.userStatus) }}</span>
						</template>
						<template v-if="column.dataIndex === 'action'">
							<a @click="form.onOpen(record)" v-if="hasPerm('bizUserEdit')">{{ $t('common.editButton') }}</a>
							<a-divider type="vertical" v-if="hasPerm(['bizUserEdit', 'bizUserDelete'], 'and')" />
							<a-popconfirm :title="$t('user.popconfirmDeleteUser')" @confirm="removeUser(record)">
								<a-button type="link" danger size="small" v-if="hasPerm('bizUserDelete')">{{
									$t('common.removeButton')
								}}</a-button>
							</a-popconfirm>
							<a-divider
								type="vertical"
								v-if="hasPerm(['bizUserGrantRole', 'bizUserPwdReset', 'bizUserExportUserInfo'])"
							/>
							<a-dropdown v-if="hasPerm(['bizUserGrantRole', 'bizUserPwdReset', 'bizUserExportUserInfo'])">
								<a class="ant-dropdown-link">
									{{ $t('common.more') }}
									<DownOutlined />
								</a>
								<template #overlay>
									<a-menu>
										<a-menu-item v-if="hasPerm('bizUserPwdReset')">
											<a-popconfirm
												:title="$t('user.popconfirmResatUserPwd')"
												placement="topRight"
												@confirm="resetPassword(record)"
											>
												<a>{{ $t('user.resetPassword') }}</a>
											</a-popconfirm>
										</a-menu-item>
										<a-menu-item v-if="hasPerm('bizUserGrantRole')">
											<a @click="selectRole(record)">{{ $t('user.grantRole') }}</a>
										</a-menu-item>
										<a-menu-item v-if="hasPerm('bizUserExportUserInfo')">
											<a @click="exportUserInfo(record)">{{ $t('user.exportUserInfo') }}</a>
										</a-menu-item>
									</a-menu>
								</template>
							</a-dropdown>
						</template>
					</template>
				</s-table>
			</a-card>
		</a-col>
	</a-row>
	<Form ref="form" @successful="table.refresh(true)" />
	<role-selector-plus
		ref="RoleSelectorPlus"
		:org-tree-api="selectorApiFunction.orgTreeApi"
		:role-page-api="selectorApiFunction.rolePageApi"
		:checked-role-list-api="selectorApiFunction.checkedRoleListApi"
		:role-global="false"
		@onBack="roleBack"
	/>
</template>
<script setup name="bizUser">
	import { message, Empty } from 'ant-design-vue'
	import tool from '@/utils/tool'
	import downloadUtil from '@/utils/downloadUtil'
	import bizUserApi from '@/api/biz/bizUserApi'
	import userCenterApi from '@/api/sys/userCenterApi'
	import roleSelectorPlus from '@/components/Selector/roleSelectorPlus.vue'
	import Form from './form.vue'

	const columns = [
		{
			title: '头像',
			dataIndex: 'avatar',
			align: 'center',
			width: '80px'
		},
		{
			title: '账号',
			dataIndex: 'account',
			ellipsis: true
		},
		{
			title: '姓名',
			dataIndex: 'name'
		},
		{
			title: '性别',
			dataIndex: 'gender',
			width: '50px'
		},
		{
			title: '手机',
			dataIndex: 'phone',
			ellipsis: true
		},
		{
			title: '机构',
			dataIndex: 'orgName',
			ellipsis: true
		},
		{
			title: '职位',
			dataIndex: 'positionName',
			ellipsis: true
		},
		{
			title: '状态',
			dataIndex: 'userStatus',
			width: '80px'
		}
	]
	if (hasPerm(['bizUserEdit', 'bizUserGrantRole', 'bizUserPwdReset', 'bizUserExportUserInfo', 'bizUserDelete'])) {
		columns.push({
			title: '操作',
			dataIndex: 'action',
			align: 'center',
			width: '220px'
		})
	}
	const toolConfig = { refresh: true, height: true, columnSetting: true }
	const statusData = tool.dictList('COMMON_STATUS')
	const searchFormRef = ref()
	let defaultExpandedKeys = ref([])
	let searchFormState = reactive({})
	const table = ref(null)
	const treeData = ref([])
	let selectedRowKeys = ref([])
	const treeFieldNames = { children: 'children', title: 'name', key: 'id' }
	let form = ref(null)
	let RoleSelector = ref()
	let RoleSelectorPlus = ref()
	const selectedRecord = ref({})
	const loading = ref(false)
	const cardLoading = ref(true)
	const ImpExpRef = ref()
	// 表格查询 返回 Promise 对象
	const loadData = (parameter) => {
		return bizUserApi.userPage(Object.assign(parameter, searchFormState)).then((res) => {
			return res
		})
	}
	// 重置
	const reset = () => {
		searchFormRef.value.resetFields()
		table.value.refresh(true)
	}
	// 左侧树查询
	bizUserApi
		.userOrgTreeSelector()
		.then((res) => {
			cardLoading.value = false
			if (res !== null) {
				treeData.value = res
				// 默认展开2级
				treeData.value.forEach((item) => {
					// 因为0的顶级
					if (item.parentId === '0') {
						defaultExpandedKeys.value.push(item.id)
						// 取到下级ID
						if (item.children) {
							item.children.forEach((items) => {
								defaultExpandedKeys.value.push(items.id)
							})
						}
					}
				})
			}
		})
		.finally(() => {
			cardLoading.value = false
		})
	// 列表选择配置
	const options = {
		alert: {
			show: false,
			clear: () => {
				selectedRowKeys = ref([])
			}
		},
		rowSelection: {
			onChange: (selectedRowKey, selectedRows) => {
				selectedRowKeys.value = selectedRowKey
			}
		}
	}
	// 点击树查询
	const treeSelect = (selectedKeys) => {
		if (selectedKeys.length > 0) {
			searchFormState.orgId = selectedKeys.toString()
		} else {
			delete searchFormState.orgId
		}
		table.value.refresh(true)
	}
	// 修改状态
	const editStatus = (record) => {
		loading.value = true
		if (record.userStatus === 'ENABLE') {
			bizUserApi
				.userDisableUser(record)
				.then(() => {
					table.value.refresh()
				})
				.finally(() => {
					loading.value = false
				})
		} else {
			bizUserApi
				.userEnableUser(record)
				.then(() => {
					table.value.refresh()
				})
				.finally(() => {
					loading.value = false
				})
		}
	}
	// 删除人员
	const removeUser = (record) => {
		let params = [
			{
				id: record.id
			}
		]
		bizUserApi.userDelete(params).then(() => {
			table.value.refresh()
		})
	}
	// 批量导出校验并加参数
	const exportBatchUserVerify = () => {
		if ((selectedRowKeys.value.length < 1) & !searchFormState.searchKey & !searchFormState.userStatus) {
			message.warning('请输入查询条件或勾选要导出的信息')
		}
		if (selectedRowKeys.value.length > 0) {
			const params = {
				userIds: selectedRowKeys.value
					.map((m) => {
						return m
					})
					.join()
			}
			exportBatchUser(params)
			return
		}
		if (searchFormState.searchKey || searchFormState.userStatus) {
			const params = {
				searchKey: searchFormState.searchKey,
				userStatus: searchFormState.userStatus
			}
			exportBatchUser(params)
		}
	}
	// 批量导出
	const exportBatchUser = (params) => {
		bizUserApi.userExport(params).then((res) => {
			downloadUtil.resultDownload(res)
			table.value.clearSelected()
		})
	}
	// 批量删除
	const deleteBatchUser = (params) => {
		bizUserApi.userDelete(params).then(() => {
			table.value.clearRefreshSelected()
		})
	}
	// 打开角色选择器
	const selectRole = (record) => {
		selectedRecord.value = record
		// 查询到已有角色，并转为ids的格式，给角色选择器
		const param = {
			id: record.id
		}
		bizUserApi.userOwnRole(param).then((data) => {
			RoleSelectorPlus.value.showRolePlusModal(data)
		})
	}
	// 角色选择回调
	const roleBack = (value) => {
		let params = {
			id: selectedRecord.value.id,
			roleIdList: []
		}
		if (value.length > 0) {
			value.forEach((item) => {
				params.roleIdList.push(item.id)
			})
		}
		bizUserApi.grantRole(params).then(() => {})
	}
	// 重置人员密码
	const resetPassword = (record) => {
		bizUserApi.userResetPassword(record).then(() => {})
	}
	// 导出用户信息
	const exportUserInfo = (record) => {
		const params = {
			id: record.id
		}
		bizUserApi.userExportUserInfo(params).then((res) => {
			downloadUtil.resultDownload(res)
		})
	}
	// 传递设计器需要的API
	const selectorApiFunction = {
		orgTreeApi: (param) => {
			return bizUserApi.userOrgTreeSelector(param).then((orgTree) => {
				return Promise.resolve(orgTree)
			})
		},
		rolePageApi: (param) => {
			return bizUserApi.userRoleSelector(param).then((data) => {
				return Promise.resolve(data)
			})
		},
		checkedRoleListApi: (param) => {
			return userCenterApi.userCenterGetRoleListByIdList(param).then((data) => {
				return Promise.resolve(data)
			})
		}
	}
</script>

<style scoped>
	.cardImp {
		margin-right: 10px;
	}
	.ant-form-item {
		margin-bottom: 0 !important;
	}
	.primaryAdd {
		margin-right: 10px;
	}
	.support-table-avatar {
		margin-top: -10px;
		margin-bottom: -10px;
	}
	.support-buttom-left {
		margin-left: 8px;
	}
</style>
