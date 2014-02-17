package task8.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Query;

import task8.databeans.LikeBean;
import task8.databeans.TopBean;

public class LikeDAO {
	public void insert(LikeBean likeBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(likeBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public void update(LikeBean likeBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(likeBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public List<TopBean> getTopLikes(int top) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from LikeBean");
		List<?> list = (List<?>) query.list();
		LikeBean[] likeBeans = list.toArray(new LikeBean[list.size()]);
		session.close();

		Map<String, Integer> count = new HashMap<String, Integer>();
		Map<String, String> source = new HashMap<String, String>();
		Map<String, String> sourceOri = new HashMap<String, String>();
		Map<String, String> title = new HashMap<String, String>();
		count.clear();

		for (int i = 0; i < likeBeans.length; i++) {
			String photo = likeBeans[i].getPhotoId();
			if (count.containsKey(photo)) {
				count.put(photo, count.get(photo) + 1);
			} else {
				count.put(photo, 1);
				source.put(photo, likeBeans[i].getImageSource());
				sourceOri.put(photo, likeBeans[i].getImageSourceOri());
				title.put(photo, likeBeans[i].getTitle());
			}
		}

		List<Integer> mapValues = new ArrayList<Integer>(count.values());
		Collections.sort(mapValues);
		
		List<TopBean> topBeans = new ArrayList<TopBean>();

		int tot = 0;
		for (int i = mapValues.size() - 1; i >= 0; i--) {
			if (tot < top) {
				for (String key : count.keySet()) {
					if (count.get(key) == mapValues.get(i)) {
						TopBean tmp = new TopBean();
						tmp.setImageSource(source.get(key));
						tmp.setImageSourceOri(sourceOri.get(key));
						tmp.setPhotoId(key);
						tmp.setCount(count.get(key));
						tmp.setTitle(title.get(key));
						topBeans.add(tmp);
						tot++;
					}
				}
			}
		}
		
		return topBeans;
	}
}