package nc.ui.so.m32.billref.it02;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.vo.querytemplate.TemplateInfo;
import java.awt.Container;

public class M32RefIT02ReferQuery extends DefaultBillReferQuery{

	public M32RefIT02ReferQuery(Container c, TemplateInfo info) {
		super(c, info);
	}

	@Override
	public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
		this.setDefaultPk_org(dlgDelegator);
		// this.initFilterRef(dlgDelegator);
		this.initBodyRedundancyItem(dlgDelegator);

		// 主组织权限
		dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] { "pk_org" });
		dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
		dlgDelegator.setPowerEnable(true);
		//dlgDelegator.setDefaultValue("vbillcode", "123");
	}

	private void initBodyRedundancyItem(QueryConditionDLGDelegator dlgDelegator) {
		
	}

	private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) {
	}
	
}
