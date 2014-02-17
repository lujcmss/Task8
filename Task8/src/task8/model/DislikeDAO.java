package task8.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Query;

import task8.databeans.DislikeBean;
import task8.databeans.TopBean;

public class DislikeDAO {
	public void insert(DislikeBean dislikeBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(dislikeBean);
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

	public void update(DislikeBean dislikeBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(dislikeBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public List<TopBean> getTopDislikes(int top) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from DislikeBean");
		List<?> list = (List<?>) query.list();
		DislikeBean[] dislikeBeans = list.toArray(new DislikeBean[list.size()]);
		session.close();

		Map<String, Integer> count = new HashMap<String, Integer>();
		Map<String, String> source = new HashMap<String, String>();
		Map<String, String> sourceOri = new HashMap<String, String>();
		Map<String, String> title = new HashMap<String, String>();
		count.clear();

		for (int i = 0; i < dislikeBeans.length; i++) {
			String photo = dislikeBeans[i].getPhotoId();
			if (count.containsKey(photo)) {
				count.put(photo, count.get(photo) + 1);
			} else {
				count.put(photo, 1);
				source.put(photo, dislikeBeans[i].getImageSource());
				sourceOri.put(photo, dislikeBeans[i].getImageSourceOri());
				title.put(photo, dislikeBeans[i].getTitle());
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