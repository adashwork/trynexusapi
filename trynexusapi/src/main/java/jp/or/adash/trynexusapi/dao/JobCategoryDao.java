package jp.or.adash.trynexusapi.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.trynexusapi.entity.JobCategory;
import jp.or.adash.trynexusapi.utils.dao.Transaction;

/**
 *  業種データアクセスクラス
 * @author aizawa
 *
 */
public class JobCategoryDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public JobCategoryDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * 業種の大分類一覧を取得する
	 * @return 業種大分類リスト
	 * @throws IOException
	 */
	public List<JobCategory> selectLargeJobCategoryList() throws IOException {
		List<JobCategory> LargeJobCategoryList = new ArrayList<JobCategory>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select largecd, name");
		sql.append(" from jobcategory");
		sql.append(" where middlecd = 0 and smallcd =0");
		sql.append(" order by largecd");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					LargeJobCategoryList.add(new JobCategory(rs.getString("largecd"),
							rs.getString("name")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return LargeJobCategoryList;
	}


	/**
	 * 業種中分類の一覧を取得する
	 * @return 業種中分類リスト
	 * @throws IOException
	 */
	public List<JobCategory> selectMiddleJobCategoryList(String largecd) throws IOException {
		List<JobCategory> middleJobCategoryList = new ArrayList<JobCategory>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select middlecd, name");
		sql.append(" from jobcategory");
		sql.append(" where largecd = ? and middlecd!=0 and smallcd = 0" );
		sql.append(" order by cast(middlecd as signed)");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			/*検索する要素*/
			ps.setString(1, largecd);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				//  取得結果をリストに格納する
				while (rs.next()) {
					middleJobCategoryList.add(new JobCategory(largecd, rs.getString("middlecd"),
							rs.getString("name")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return middleJobCategoryList;
	}


	/**
	 * 業種小分類の一覧を取得する
	 * @return 業種小分類リスト
	 * @throws IOException
	 */
	public List<JobCategory> selectSmallJobCategoryList(String middlecd) throws IOException {
		List<JobCategory> smallJobCategoryList = new ArrayList<JobCategory>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select smallcd, name");
		sql.append(" from jobcategory");
		sql.append(" where middlecd = ? and smallcd !=0");
		sql.append(" order by cast(smallcd as signed)");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			/*検索する要素*/

			ps.setString(1, middlecd);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				//  取得結果をリストに格納する
				while (rs.next()) {
					smallJobCategoryList.add(new JobCategory(null, null, middlecd, rs.getString("smallcd"),
							rs.getString("name")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return smallJobCategoryList;
	}

}
