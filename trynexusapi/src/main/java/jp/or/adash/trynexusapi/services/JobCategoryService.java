/**
 *
 */
package jp.or.adash.trynexusapi.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.trynexusapi.dao.JobCategoryDao;
import jp.or.adash.trynexusapi.entity.JobCategory;
import jp.or.adash.trynexusapi.utils.dao.Transaction;

/**
 * 業種に関する処理を定義するクラス
 * @author aizawa
 * @author akiba(2018.12.14)
 */
public class JobCategoryService {

	/**
	 * トランザクションオブジェクト
	 */
	private Transaction transaction;

	/**
	 * 処理結果メッセージを格納するリスト
	 */
	private List<String> messages;

	/**
	 * コンストラクタ
	 */
	public JobCategoryService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * 業種大分類リストを取得する
	 * @param code
	 * @return 業種リスト
	 */
	public List<JobCategory> getLargeJobCategoryList() {
		List<JobCategory> largeJobCategoryList = new ArrayList<JobCategory>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 大分類リストを取得する
			JobCategoryDao dao = new JobCategoryDao(transaction);
			largeJobCategoryList = dao.selectLargeJobCategoryList();

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return largeJobCategoryList;
	}

	/**
	 * 業種中分類リストを取得する
	 * @param code
	 * @return 業種リスト
	 */
	public List<JobCategory> getMiddleJobCategoryList(String largeCode) {
		List<JobCategory> middleJobCategoryList = new ArrayList<JobCategory>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 中分類リストを取得する
			JobCategoryDao dao = new JobCategoryDao(transaction);
			middleJobCategoryList = dao.selectMiddleJobCategoryList(largeCode);

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return middleJobCategoryList;
	}
	/**
	 * 業種小分類リストを取得する
	 * @return 業種リスト
	 */
	public List<JobCategory> getSmallJobCategoryList(String middleCode) {
		List<JobCategory> smallJobCategoryList = new ArrayList<JobCategory>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 小分類リストを取得する
			JobCategoryDao dao = new JobCategoryDao(transaction);
			smallJobCategoryList = dao.selectSmallJobCategoryList(middleCode);

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return smallJobCategoryList;
	}

}
