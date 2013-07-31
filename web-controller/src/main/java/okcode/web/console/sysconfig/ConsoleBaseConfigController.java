package okcode.web.console.sysconfig;

import okcode.biz.trading.dto.BaseConfigDto;
import okcode.biz.trading.intf.BaseConfigService;
import okcode.framework.result.Result;
import okcode.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 门户商品请求controller
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = "/console/baseConfig")
public class ConsoleBaseConfigController extends BaseController {
	
	@Autowired
	private BaseConfigService baseConfigService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(CONSOLE + "baseConfig/index");
		mav.addObject("dto", baseConfigService.findBaseConfig());
		return mav;
	}
	
	@RequestMapping(value = "/saveBaseConfig", method = RequestMethod.POST)
	@ResponseBody
	public Result saveBaseConfig(BaseConfigDto dto) {
		baseConfigService.saveBaseConfig(dto);
		Result result = new Result();
		return result;
	}
	
	@RequestMapping(value = "emailConfig", method = RequestMethod.GET)
	public ModelAndView emailConfig() {
		ModelAndView mav = new ModelAndView(CONSOLE + "baseConfig/emailConfig");
		return mav;
	}
}