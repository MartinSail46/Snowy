import configApi from '@/api/dev/configApi'
import { message } from 'ant-design-vue'

const formData = ref({
	SUPPORT_SYS_LOGO: '',
	SUPPORT_SYS_BACK_IMAGE: '',
	SUPPORT_SYS_NAME: '',
	SUPPORT_SYS_VERSION: '',
	SUPPORT_SYS_COPYRIGHT: '',
	SUPPORT_SYS_COPYRIGHT_URL: '',
	SUPPORT_SYS_DEFAULT_FILE_ENGINE: 'LOCAL',
	SUPPORT_SYS_DEFAULT_CAPTCHA_OPEN: false,
	SUPPORT_SYS_DEFAULT_PASSWORD: ''
})

const param = {
	category: 'SYS_BASE'
}

const getSysBaseConfig = () => {
	configApi.configList(param).then((data) => {
		if (data) {
			data.forEach((item) => {
				formData.value[item.configKey] = item.configValue ? '' : item.configValue
			})
		} else {
			message.warning('表单项不存在，请初始化数据库')
		}
	})
}
