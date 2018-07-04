package nc.bs.so.m33.biz.m4c.rule.toia;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * ȡ��������Ʒʱ��ҵ��У��
 * @scene
 * ȡ��������Ʒǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-9-20 ����06:04:21
 * @author zhangcheng
 */
public class CheckForCancelREG4CRule implements IRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] vos) {
    String[] outbids =
        SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSQUAREBILLBID);
    String[] outhids =
        SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSQUAREBILLID);
    String[] querykeys = new String[] {
      SquareInvBVO.CSRCBID, SquareInvBVO.NSQUAREREGNUM
    };
    SquareInvViewVO[] invviews =
        new QuerySquare32VOBP().querySquareInvViewVOBy4CBIDHID(outbids,
            outhids, querykeys);
    // <���ⵥ����id,SquareInvViewVO>
    Map<String, SquareInvViewVO> minv = new HashMap<String, SquareInvViewVO>();
    for (SquareInvViewVO view : invviews) {
      minv.put(view.getItem().getCsrcbid(), view);
    }

    // ���η�Ʊ�Ѿ��ɱ����㣬�򲻿���ȡ��������Ʒ
    for (SquareOutViewVO view : vos) {
      SquareInvViewVO invview = minv.get(view.getItem().getCsquarebillbid());
      // ��Ʊ�ɱ��������������Ʒ�跽
      UFDouble ndowninvdebit = UFDouble.ZERO_DBL;
      if (!SOVOChecker.isEmpty(invview)) {
        ndowninvdebit = invview.getItem().getNsquareregnum();
      }
      UFDouble ndownrushnum = view.getItem().getNrushnum();
      SquareOutHVO hvo = view.getHead();
      String billno = hvo.getVbillcode();
      if (!MathTool.isZero(ndowninvdebit) || !MathTool.isZero(ndownrushnum)) {
       // ExceptionUtils.wrappBusinessException(billno
           // + "���ⵥ���η�Ʊ�Ѿ��ɱ�������߳���Գ壬�򲻿���ȡ��������Ʒ");
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4006010_0", "04006010-0141",null,new String[]{billno})/*{0}���ⵥ���η�Ʊ�Ѿ��ɱ�������߳���Գ壬�򲻿���ȡ��������Ʒ
*/);
      }
    }

  }

}