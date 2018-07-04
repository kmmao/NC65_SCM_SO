package nc.itf.so.m32;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.et.fgjsdld.AggFgjsdldHeadVO;
import nc.vo.et.gcdljsd.AggGcdljsdHeadVO;
import nc.vo.it.ctjsd.AggCtjsdHeadVO;
import nc.vo.it.yljsd.AggYljsd;
import nc.vo.lm.jkyldlbzdzb1.AggJkyldlbzdzb1HeadVO;
import nc.vo.lm.yffyjsd.AggYffyjsdHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.goldtax.GoldTaxVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�����ӿڣ�
 * <li>���۷�Ʊ��ѯ
 * <li>���۷�Ʊ��������
 * <li>���۷�Ʊ�޸ı���
 * <li>���۷�Ʊ����ɾ��
 * <li>���۷�Ʊ�������
 * <li>���۷�Ʊ���� ����
 * </ul>
 * 
 * @version ���汾��6.0
 * @author fengjb
 * @time 2010-1-19 ����09:31:26
 */
public interface ISaleInvoiceMaintain {

	/**
	 * �����������������۷�Ʊ������˹��ܡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param appvos
	 *            ����˵����۷�Ʊ�ۺ�VO����
	 * @return ��˺�������VO���飬��������������״̬��ʱ�����
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 ����09:38:53
	 */
	SaleInvoiceVO[] approveSaleInvoice(SaleInvoiceVO[] appvos)
			throws BusinessException;

	/**
	 * �����������������۷�Ʊ����ɾ�����ܡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param delvos
	 *            ��ɾ������������ƱVO����
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 ����09:37:16
	 */
	void deleteSaleInvoice(SaleInvoiceVO[] delvos) throws BusinessException;

	/**
	 * ִ�д���˰��VO����
	 * 
	 * @param hids
	 *            ���۷�Ʊhid
	 * @return ��˰vo
	 * @throws BusinessException
	 */
	GoldTaxVO[] executeVOChangeTogtax(String[] hids) throws BusinessException;

	/**
	 * �������������ѯ����ID
	 * 
	 * @param innercode
	 * @return key innercode value Ϊ����id
	 * 
	 * @throws BusinessException
	 */
	Map<String, String> getCmaterialids(String[] innercode)
			throws BusinessException;

	/**
	 * �������ϲ�ѯ����
	 * 
	 * @param cmaterialids
	 * @return key Ϊ����id value innercode
	 * @throws BusinessException
	 */
	Map<String, String> getInnercodemaps(String[] cmaterialids)
			throws BusinessException;

	/**
	 * �����������������۷�Ʊ�������湦�ܡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param newvos
	 *            ��������������۷�Ʊ�ۺ�VO
	 * @return ������������VO���������������ݺš�ʱ�����
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 ����09:34:38
	 */
	SaleInvoiceVO[] insertSaleInvoice(SaleInvoiceVO[] newvos)
			throws BusinessException;

	/**
	 * �����������������۷�Ʊ��ѯ���ܡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param whereSql
	 *            ��ѯʹ�õĹ�������
	 * @return ����Ҫ��ķ�Ʊ�ۺ�VO
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 ����09:33:12
	 */
	SaleInvoiceVO[] querySaleInvoice(String whereSql) throws BusinessException;

	/**
	 * ���۷�Ʊ�ṩ������ת����ѯ
	 * 
	 * @param queryScheme
	 * @return ��Ʊvo
	 * @throws BusinessException
	 */
	SaleInvoiceVO[] querySaleInvoiceFor4C(IQueryScheme queryScheme)
			throws BusinessException;

	/**
	 * 
	 * @param sql
	 * @return ���۷�Ʊ��ͼvo
	 * @throws BusinessException
	 */
	SaleInvoiceViewVO[] queryViewvo(String sql) throws BusinessException;

	/**
	 * �����������������۷�Ʊ���������ܡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param unappvos
	 *            ����������۷�Ʊ�ۺ�VO����
	 * @return �����������VO���飬��������������״̬��ʱ�����
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 ����09:47:30
	 */
	SaleInvoiceVO[] unapproveSaleInvoice(SaleInvoiceVO[] unappvos)
			throws BusinessException;

	/**
	 * �������������������˰Ʊ��ʱ�������ݿ����۷�Ʊ��˰Ʊ�š�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param voHeads
	 * @return ��Ʊ��ͷvo
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-8-24 ����07:05:03
	 */
	SaleInvoiceHVO[] updateGoldTaxCode(SaleInvoiceHVO[] voHeads)
			throws BusinessException;

	/**
	 * �����������������۷�Ʊ�޸ı��湦�ܡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param updatevos
	 *            ���޸ı�������۷�Ʊ�ۺ�VO
	 * @return ������������VO���������������ݺš�ʱ�����
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 ����09:35:59
	 */
	SaleInvoiceVO[] updateSaleInvoice(SaleInvoiceVO[] updatevos)
			throws BusinessException;

	/**
	 * �����������������۷�Ʊ����˰ʱ���º�̨���ݿ���Ϣ��
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param voHeads
	 * @return ��Ʊ��ͷvo
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-8-24 ����03:13:22
	 */
	SaleInvoiceHVO[] updateWhenExportGoldTax(SaleInvoiceHVO[] voHeads)
			throws BusinessException;

	/**
	 * �����������������۷�Ʊ��ѯ���ܡ�
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param hids
	 *            ��������ID���VO
	 * @return ����Ҫ��ķ�Ʊ�ۺ�VO
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 ����09:33:12
	 */
	SaleInvoiceVO[] querySaleInvoice(String[] hids) throws BusinessException;
	
	//ԭ�����۽��㵥����
	public AggYljsd[] queryIT02ForM32(IQueryScheme queryScheme) throws BusinessException;
	//�������۽��㵥����
	public AggCtjsdHeadVO[] queryIT01ForM32(IQueryScheme queryScheme) throws BusinessException;
	//�ֲĴ������㵥
	public AggGcdljsdHeadVO[] queryET03ForM32(IQueryScheme queryScheme) throws BusinessException;
	//�Ǹִ������㵥
	public AggFgjsdldHeadVO[] queryET02ForM32(IQueryScheme queryScheme) throws BusinessException;
	//����ԭ�ϱ��˵�
	public AggJkyldlbzdzb1HeadVO[] queryLM40ForM32(IQueryScheme queryScheme) throws BusinessException;
	//Ӧ�����ý��㵥
	public AggYffyjsdHeadVO[] queryLM21ForM32(IQueryScheme queryScheme) throws BusinessException;
	
	void deteleysd(String pk);
	
	void updatexsfp(String pk);
}