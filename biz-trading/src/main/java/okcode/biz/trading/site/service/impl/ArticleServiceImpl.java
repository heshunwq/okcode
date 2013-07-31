package okcode.biz.trading.site.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import okcode.biz.trading.enums.Module;
import okcode.biz.trading.intf.ArticleService;
import okcode.biz.trading.model.Article;
import okcode.biz.trading.model.Catalog;
import okcode.biz.trading.site.dao.ArticleDao;
import okcode.biz.trading.site.dao.CatalogDao;
import okcode.biz.trading.site.dao.SpecificDao;
import okcode.biz.trading.util.BizKeyValue;
import okcode.framework.exception.AppException;
import okcode.framework.exception.ErrorCode;
import okcode.service.standard.intf.CountService;
import okcode.service.standard.model.Count;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private CatalogDao catalogDao;
	
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private SpecificDao specificDao;

	@Autowired
	private CountService countService;
	
	@Override
	public Article saveArticle(Article article) {
		Article entity = null;
		if (article.getId() == null || article.getId() == 0) {
			entity = new Article();
			entity.setCreateAt(new Date());
		} else {
			entity = articleDao.findOne(article.getId());
			if (entity == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "更新的对象未找到");
		}
		
		Catalog catalog = catalogDao.findOne(article.getCatalogId());
		if (catalog == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "对应的栏目不存在！");
		article.setCatalog(catalog);
		
		Module module = catalog.getModule();
		//to save
		if (module.ordinal() == Module.article.ordinal())
			saveArticleModule(article, entity);
		else if (module.ordinal() == Module.article.ordinal())
			saveProductModule(article, entity);
		return entity;
	}
	
	private void saveArticleModule(Article article, Article entity) {
		entity.setTopFlag(article.getTopFlag() == null ? false : article.getTopFlag());
//		entity.setAttributes(attributes);
		entity.setAuthor(article.getAuthor());
		entity.setContent(article.getContent());
		entity.setSubTitle(article.getSubTitle());
		entity.setTitle(article.getTitle());
		entity.setUpdateAt(article.getUpdateAt() == null ? new Date() : article.getUpdateAt());
		entity.setCatalog(article.getCatalog());
		
		articleDao.save(entity);
		long clicks = article.getClicks() == null ? 0 
				: (article.getClicks() <= 0 ? 0 : article.getClicks() );
		countService.saveCount(BizKeyValue.COUNT_SERVICE_ARTICLE_CLICKS.toString(), entity.getId()+"", clicks);
	}
	
	private void saveProductModule(Article product, Article entity) {
	}

	@Override
	public Article deleteArticle(Long articleId) {
		Article article = articleDao.findOne(articleId);
		if (article == null)
			return null;
		articleDao.delete(article);
		return article;
	}
	
//	private void setClicks(List<Article> list) {
//		if (!CollectionUtils.isEmpty(list)) {			
//			List<String> ids = new ArrayList<String>();
//			for (Article article : list)
//				ids.add(article.getId()+"");
//			Map<String, Count> counts = countService.findCounts(BizKeyValue.COUNT_ARTICLE_CLICKS.toString(), ids);
//			for (Article article : list)
//				article.setClicks(counts.get(article.getId()+"").getNum());
//		}
//	}
	
	private void setClicks(Article article) {
		if (article == null)
			return;
		Count count = countService.findCount(BizKeyValue.COUNT_SERVICE_ARTICLE_CLICKS.toString(), article.getId()+"");
		if (count != null)
			article.setClicks(count.getNum());
	}
	
	@Override
	public Article findById(Long id) {
		Article article = articleDao.findOne(id);
		setClicks(article);
		return article;
	}

	@Override
	public List<Article> findByIds(Collection<Long> ids) {
		return articleDao.findByIdInOrderByUpdateAtDesc(ids);
	}
	
	@Override
	public List<Article> findByCatalogId(Collection<Long> catalogIds) {
		return articleDao.findByCatalogIdInOrderByUpdateAtDesc(catalogIds);
	}

	@Override
	public Page<Article> list(Module module, Collection<Long> catalogIds,String key,Pageable pageable) {
		List<Article> list = specificDao.listArticle(module, catalogIds, key, 
				(pageable == null ? -1:pageable.getOffset()), 
				(pageable == null ? -1:pageable.getPageSize()) );
		long total = specificDao.countArticle(module, catalogIds, key);
		Page<Article> page = new PageImpl<Article>(list, pageable, total);
		return page;
	}
	
	

}