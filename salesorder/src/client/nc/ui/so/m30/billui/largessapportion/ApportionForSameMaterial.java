package nc.ui.so.m30.billui.largessapportion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 同物料分摊的分摊方式
 * 
 * @since 6.0
 * @version 2011-7-13 下午01:03:08
 * @author fengjb
 */

public class ApportionForSameMaterial implements IApportionAction {

  @Override
  public void apportion(BillCardPanel cardPanel, List<Integer> rowlist,
      boolean istaxprior) {

    boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
    cardPanel.getBodyPanel().setTotalRowShow(false);

    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String mnyitemkey = SaleOrderBVO.NORIGMNY;
    if (istaxprior) {
      mnyitemkey = SaleOrderBVO.NORIGTAXMNY;
    }

    // 获取赠品行、正常销售行、金额合计
    List<Integer> largessrows = new ArrayList<Integer>();
    List<Integer> normalrows = new ArrayList<Integer>();

    Map<Integer, UFDouble> rownummap = new HashMap<Integer, UFDouble>();

    UFDouble normalsummny = UFDouble.ZERO_DBL;
    UFDouble sumnum = UFDouble.ZERO_DBL;

    for (Integer rowindex : rowlist) {
      UFBoolean larflag =
          keyValue.getBodyUFBooleanValue(rowindex.intValue(),
              SaleOrderBVO.BLARGESSFLAG);
      UFDouble rowmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(), mnyitemkey);
      // 赠品行
      if (null != larflag && larflag.booleanValue()) {
        largessrows.add(rowindex);
      }
      else {
        normalrows.add(rowindex);
        normalsummny = MathTool.add(normalsummny, rowmny);
      }

      UFDouble num =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(), SaleOrderBVO.NNUM);
      sumnum = MathTool.add(sumnum, num);
      rownummap.put(rowindex, num);
    }

    // 没有赠品，不用分摊
    if (largessrows.size() == 0) {
      return;
    }
    // 只有赠品行，不用分摊
    if (normalrows.size() == 0) {
      return;
    }
    //
    if (MathTool.isZero(sumnum)) {
      return;
    }

    // 进行金额分摊
    Map<Integer, UFDouble> rowmnymap = new HashMap<Integer, UFDouble>();
    UFDouble price = normalsummny.div(sumnum);
    UFDouble tempsummny = UFDouble.ZERO_DBL;
    // 获得币种精度用于控制中间计算
    String corigcurr =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);

    for (int i = 0; i < largessrows.size(); i++) {
      UFDouble num = rownummap.get(largessrows.get(i));
      UFDouble rowmny =
          UIScaleUtils.getScaleUtils().adjustMnyScale(num.multiply(price),
              corigcurr);
      rowmnymap.put(largessrows.get(i), rowmny);
      tempsummny = MathTool.add(tempsummny, rowmny);
    }
    Collections.sort(normalrows);

    for (int i = 0; i < normalrows.size() - 1; i++) {
      UFDouble num = rownummap.get(normalrows.get(i));
      UFDouble rowmny =
          UIScaleUtils.getScaleUtils().adjustMnyScale(num.multiply(price),
              corigcurr);
      rowmnymap.put(normalrows.get(i), rowmny);
      tempsummny = MathTool.add(tempsummny, rowmny);
    }
    // 尾差处理
    UFDouble lastrowmny = MathTool.sub(normalsummny, tempsummny);
    rowmnymap.put(normalrows.get(normalrows.size() - 1), lastrowmny);

    // 设置结果：金额、赠品、分摊标记
    for (Integer rowindex : normalrows) {
      // 分摊前无税金额
      UFDouble origmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSMNY,
          origmny);
      // 分摊前价税合计
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGTAXMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSTAXMNY,
          origtaxmny);
      // 分摊后金额
      keyValue.setBodyValue(rowindex.intValue(), mnyitemkey,
          rowmnymap.get(rowindex));
      // 分摊标志
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.FLARGESSTYPEFLAG,
          Largesstype.APPORTIONMATERIAL.value());
    }
    for (Integer rowindex : largessrows) {
      // 分摊前无税金额
      UFDouble origmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSMNY,
          origmny);
      // 分摊前价税合计
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGTAXMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSTAXMNY,
          origtaxmny);
      // 分摊后金额
      keyValue.setBodyValue(rowindex.intValue(), mnyitemkey,
          rowmnymap.get(rowindex));
      // 分摊标志
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.FLARGESSTYPEFLAG,
          Largesstype.APPORTIONLARGESS.value());
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.BLARGESSFLAG,
          UFBoolean.FALSE);
    }
    cardPanel.getBodyPanel().setTotalRowShow(istotalshow);
  }

}
