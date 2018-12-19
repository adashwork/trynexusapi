package jp.or.adash.trynexusapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.or.adash.trynexusapi.entity.JobCategory;
import jp.or.adash.trynexusapi.services.JobCategoryService;

/**
 * 企業ページの業種プルダウンに関するWeb-APIクラス
 * @author akiba
 *
 */
@Path("/jobcategory")
public class JobCategoryResource {

	/**
	 * 業種カテゴリーを取得
	 * @param code 業種の大分類
	 * @return 業種の中分類のJSONオブジェクト
	 */
	@GET
	@Path("/getjobcategory")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JobCategory> getJobCategory(@QueryParam("largecode") String largeCode, @QueryParam("middlecd") String middlecd) {
		// 分類データを取得
		JobCategoryService jobCategoryService = new JobCategoryService();
		List<JobCategory> middleCategory = jobCategoryService.getMiddleJobCategoryList(largeCode);
		return middleCategory;
	}



	/**
	 * 業種カテゴリーを取得
	 * @param code 業種の中分類
	 * @return 業種の小分類のJSONオブジェクト
	 */
	@GET
	@Path("/getjobcatagory2")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JobCategory> getJobCategory2(@QueryParam("middlecode") String middleCode) {
		// 分類データを取得
		JobCategoryService jobCategoryService = new JobCategoryService();
		List<JobCategory> smallCategory = jobCategoryService.getSmallJobCategoryList(middleCode);
		return smallCategory;
	}
}