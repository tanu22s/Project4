package in.co.rays.ors.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

import in.co.rays.ors.bean.BaseBean;
import in.co.rays.ors.bean.CollegeBean;
import in.co.rays.ors.bean.CourseBean;
import in.co.rays.ors.bean.FacultyBean;
import in.co.rays.ors.bean.SubjectBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModel;
import in.co.rays.ors.model.CourseModel;
import in.co.rays.ors.model.FacultyModel;
import in.co.rays.ors.model.SubjectModel;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * 
 * 
 * @author Tanvi
 * @Version:(4.14.0)
 * 
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FacultyCtl.class);

	protected void preload(HttpServletRequest request) {

		System.out.println("preload  in ");

		CourseModel cmodel = new CourseModel();
		CollegeModel comodel = new CollegeModel();
		SubjectModel smodel = new SubjectModel();

		List<CourseBean> clist = new ArrayList<CourseBean>();
		List<CollegeBean> colist = new ArrayList<CollegeBean>();
		List<SubjectBean> slist = new ArrayList<SubjectBean>();

		try {
			clist = cmodel.list();
			colist = comodel.list();
			slist = smodel.list();

			request.setAttribute("CourseList", clist);
			request.setAttribute("CollegeList", colist);
			request.setAttribute("SubjectList", slist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validate(HttpServletRequest request) {

		System.out.println("validate  in ");

		log.debug("Validate Method of Faculty Ctl Started");
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("firstname"))) {
			request.setAttribute("firstname", PropertyReader.getValue("error.require", "FirstName"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("firstname"))) {
			request.setAttribute("firstname", PropertyReader.getValue("error.name", "Invalid First"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastname"))) {
			request.setAttribute("lastname", PropertyReader.getValue("error.require", "LastName"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("lastname"))) {
			request.setAttribute("lastname", PropertyReader.getValue("error.name", "Invalid Last"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("doj"))) {
			request.setAttribute("doj", PropertyReader.getValue("error.require", "Date of Joining"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("loginid"))) {
			request.setAttribute("loginid", PropertyReader.getValue("error.require", "LoginId"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("loginid"))) {
			request.setAttribute("loginid", PropertyReader.getValue("error.email", "Invalid"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("collegeid"))) {
			request.setAttribute("collegeid", PropertyReader.getValue("error.require", "CollegeName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("courseid"))) {
			request.setAttribute("courseid", PropertyReader.getValue("error.require", "CourseName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("subjectid"))) {
			request.setAttribute("subjectid", PropertyReader.getValue("error.require", "SubjectName"));
			pass = false;
		}

		System.out.println("validate out ");
		log.debug("validate Ended");
		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("populate bean faculty ctl started");
		System.out.println(" populate bean ctl  in ");
		FacultyBean bean = new FacultyBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstname")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastname")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setQualification(DataUtility.getString(request.getParameter("qualification")));
		bean.setLoginId(DataUtility.getString(request.getParameter("loginid")));
		bean.setDateofjoining(DataUtility.getDate(request.getParameter("doj")));

//		System.out.println("populate bean ..."+request.getParameter("doj"));

		bean.setMobileno(DataUtility.getString(request.getParameter("mobileno")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setCollegeid(DataUtility.getLong(request.getParameter("collegeid")));
		// bean.setCollegeName(DataUtility.getString(request.getParameter("collegename")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseid")));
		// bean.setCourseName(DataUtility.getString(request.getParameter("coursename")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectid")));
		// bean.setSubjectName(DataUtility.getString(request.getParameter("subjectname")));

		populateDTO(bean, request);
		log.debug("populate bean faculty ctl Ended");
		System.out.println(" populate bean ctl out ");
		return bean;
	}

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("Do get of faculty ctl Started");
		String op = DataUtility.getString(request.getParameter("operation"));

		// Get Model
		FacultyModel model = new FacultyModel();
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			FacultyBean bean;
			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);

			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		System.out.println(" do get out ");
		log.debug("Do get of  faculty ctl Ended");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("Do post of  faculty ctl Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// Get Model
		FacultyModel model = new FacultyModel();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			FacultyBean bean = (FacultyBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
				} else {
					long pk = model.add(bean);
					// bean.setId(pk);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Faculty Successfully Register", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Faculty already Exist", request);
			}
		} /*
			 * else if (OP_DELETE.equalsIgnoreCase(op)) { FacultyBean bean =(FacultyBean)
			 * populateBean(request);
			 * 
			 * try { model.delete(bean); ServletUtility.redirect(ORSView.FACULTY_CTL,
			 * request, response); } catch (ApplicationException e) { log.error(e);
			 * ServletUtility.handleException(e, request, response); return; } }
			 */ else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		System.out.println(" do post out ");
		ServletUtility.forward(getView(), request, response);
		log.debug("Do post of  faculty ctl Ended");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
