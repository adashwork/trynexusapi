package jp.or.adash.trynexusapi.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 業種(産業分類)マスタのEntityクラス
 * @author pgjavaAT
 *
 */

@XmlRootElement
public class JobCategory {

	/**
	 * id 職種ID
	 * largecd 大分類コード
	* middlecd 中分類コード
	* smallcd 小分類コード
	* name 業種名
	 */
	private Integer id;
	private String largecd;
	private String middlecd;
	private String smallcd;
	private String name;
	public JobCategory(){
	}
	public JobCategory(Integer id, String largecd, String middlecd, String smallcd, String name) {
		this.id = id;
		this.largecd = largecd;
		this.middlecd = middlecd;
		this.smallcd = smallcd;
		this.name = name;
	}

	public JobCategory(String largecd, String name) {
		this.largecd = largecd;
		this.name = name;
	}
	public JobCategory(String largecd, String middlecd, String name) {
		this.middlecd = middlecd;
		this.name = name;
	}


	/**
	 * idを返す
	 * @return id
	 */
	@XmlElement
	public Integer getId() {
		return id;
	}

	/**
	 * 大分類コードを返す
	 * @return largecd
	 */
	@XmlElement
	public String getLargecd() {
		return largecd;
	}

	/**
	 * 中分類コードを返す
	 * @return middlecd
	 */
	@XmlElement
	public String getMiddlecd() {
		return middlecd;
	}

	/**
	 * 小分類コードを返す
	 * @return smallcd
	 */
	@XmlElement
	public String getSmallcd() {
		return smallcd;
	}

	/**
	 * 業種名を返す
	 * @return name
	 */
	@XmlElement
	public String getName() {
		return name;
	}

}
