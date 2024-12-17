package com.capstone.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.Config.Emailconfig;
import com.capstone.DAO.AddressDao;
import com.capstone.DAO.DrugDao;
import com.capstone.DAO.OrdersDAO;
import com.capstone.DAO.PaymentDao;
import com.capstone.Entity.Address;
import com.capstone.Entity.Admin;
import com.capstone.Entity.Drug;
import com.capstone.Entity.Member;
import com.capstone.Entity.Orders;
import com.capstone.Entity.Payments;

import com.capstone.Repository.PaymentRepository;
import com.capstone.Service.DrugService;
import com.capstone.Service.Orderssarvice;
import com.capstone.util.SuccessResponse;
@Controller
@RequestMapping("/home")
public class Homepage {
	//http://localhost:8080/home/pharmahomepage
	@Autowired
	MemberController m;
	@Autowired
	AddressDao ad;
	@Autowired
	DrugDao dao;
	@Autowired
	PaymentsController pc;
	@Autowired
	PaymentDao pdao;
	@Autowired
	Payments p;
	@Autowired
	PaymentRepository pr;
	@Autowired
	OrdersDAO od;
	
	@Autowired
	Orderssarvice ose;
	
	@Autowired
	Emailconfig emailconfig;
	
	@Autowired
	OrderContraller oc;
	
	int mid;
	String uname="";
	String adminsname="";
	String drugname;
	double drugprice;
	@GetMapping("/pharmahomepage")
  public String homepage()
  {
	  return "PharmaHomePage.html";
  }
	
	
	@GetMapping("/login")
	public String loginpage()
	  {
		  return "memberlogin.html";
	  }
	@GetMapping("/home")
	public String home(String email, String password, Model model) {
	    // Correct redirection to match view resolution
		 model.addAttribute("username", uname);
		 List<Drug> allDrugs = dao.allDrugs();
	      model.addAttribute("all", allDrugs);
	    return "userhomepage";
	}


	@PostMapping("/alldrugs")
	public String userLogin(String email, String password, Model model) {
	    Member memberlogin ;


	    try {
	        memberlogin = m.memberlogin(email, password);
	    } catch (Exception e) {
	        System.out.println("Exception during login: " + e.getMessage());
	        model.addAttribute("invalid", "Invalid Credentials");
	        return "memberlogin"; // Return login page
	    }

	    if (memberlogin ==null) {
	       System.out.println("Invalid Credentials");
	        model.addAttribute("invalid", "Invalid Credentials...");
	        return "memberlogin"; // Login template
	        
	    } 
	    	 String username = memberlogin.getName(); 
		        model.addAttribute("username", username);
		        uname=memberlogin.getName();
		        mid=memberlogin.getId();
		        List<Drug> allDrugs = dao.allDrugs();
		        model.addAttribute("all", allDrugs);
		        return "userhomepage";
	    
	}

	@RequestMapping("/filterdetails")
	public String filterDrugs( String name, Model model) {
	    List<Drug> filter = dao.filter(name);
	    if (filter.isEmpty()) {
	        model.addAttribute("noitems", "No medicines are found...");
	    } else {
	        model.addAttribute("all", filter);
	    }
	    model.addAttribute("username", uname);
	    model.addAttribute("adminname", adminsname);
	    return "userhomepage";
	}
	@RequestMapping("/filterdrugdetails")
	public String filteradminDrugs( String name, Model model) {
	    List<Drug> filter = dao.filter(name);
	    if (filter.isEmpty()) {
	        model.addAttribute("noitems", "No medicines are found...");
	    } else {
	        model.addAttribute("all", filter);
	    }
	    model.addAttribute("username", uname);
	    model.addAttribute("adminname", adminsname);
	    return "adminhomepage.html";
	}
	@RequestMapping("/registrationform")
	public String registration(Model model)
	{
		Member me=new Member();
		Address address=new Address();
		model.addAttribute("member",me);
		model.addAttribute("address",address);
		return "PharmaRegistration.html";
	}

	
	@GetMapping("/registration")
	public String registration(Member mc,Address a)
	{

		mc.setAddress(a);
		mc.setDisabled(true);
		ResponseEntity<SuccessResponse> savemember = m.saveAdmin(mc);
		
		
		Address saveaddress = ad.saveaddress(a);
//		if(savemember.getClass() !=null && saveaddress !=null)
//		{
			emailconfig.sendmail(mc.getEmail(), "Registration Successfully Completed", "Welcome to PharamCare");
		return "memberlogin";
//		}else
//		{
//			return null;
//		}
	}
	@Autowired
	AdminsController ac;
	
	
	@GetMapping("/adminlogin")	
	public String adminLogin()
	{
		return "AdminLogin.html";
	}
	int aid=0;
	String adminemail="";
	@GetMapping("/adminhome")
	public String adminhomepage(String email,String password,Model model)
	{
		Admin login ;
		 try {
		       login= ac.login(email, password);
		       aid=login.getId();
		       adminemail=login.getEmail();
		    } catch (Exception e) {
		        System.out.println("Exception during login: " + e.getMessage());
		        model.addAttribute("msg", "Invalid Credentials");
		        return "AdminLogin.html"; // Return login page
		    }
		if(login.getClass()!=null)
		{
			String adminname = login.getName();
		        model.addAttribute("adminname", adminname);
		        adminsname=login.getName();
		        List<Drug> allDrugs = dao.allDrugs();
		        model.addAttribute("all", allDrugs);
		        return "adminhomepage.html";
			
		}else
		{
			model.addAttribute("msg","invalid credintials");
			return "AdminLogin.html";
		}
	}
	@GetMapping("/AdminHome")
	public String adminshomepage(String email,String password,Model model)
	{
			model.addAttribute("adminname", adminsname);
	        List<Drug> allDrugs = dao.allDrugs();
	        model.addAttribute("all", allDrugs);
	        return "adminhomepage.html";
	}
	@Autowired
	DrugService ds;
	@GetMapping("/addnewdrug")
	public String addnewdrug(Model model)
	{
		Drug d=new Drug();
		model.addAttribute("drugobj",d);
		return "NewDrug.html";
	}
	@GetMapping("/savingdrug")
	public String addingnewdrug(Drug d,Model model)
	{
		d.setRating(4);
		ResponseEntity<SuccessResponse> save = ds.save(d);
		if(save.getClass() !=null)
		{
				model.addAttribute("adminname", adminsname);
		        List<Drug> allDrugs = dao.allDrugs();
		        model.addAttribute("all", allDrugs);
		        model.addAttribute("drugmsg","Drug deleted  successfully");
		       
		        return "adminhomepage.html";
			
			
		}else
		{
			model.addAttribute("ivalid","Drug is not  added ");
			return "NewDrug.html";
		}
	}
	
	@RequestMapping("/deletedrug")
	public String deletedrug(@RequestParam("drugId") int drugId,Model model)
	{
		
		ds.deletedrug(drugId);
		 model.addAttribute("adminname", adminsname);
	        List<Drug> allDrugs = dao.allDrugs();
	        model.addAttribute("all", allDrugs);
	        model.addAttribute("drugmsg","Drug deleted  successfully");
		return "adminhomepage.html";
	}
	int drug_id=0;
	@RequestMapping("/updatedrug")
	public String updatedruug(Model model,@RequestParam("drugId") int drugId)
	{
		drug_id=drugId;
		Drug d=new Drug();
		model.addAttribute("dobj",d);
		return "updatedrug.html";
	}
	@GetMapping("/updationofdrug")
	public String updadtion(@RequestParam(required = false, defaultValue = "0.0") double price,
		    @RequestParam(required = false, defaultValue = "0") int quantity,Model model
		    )
	{
		System.out.println("----------------");
		System.out.println(drug_id);
		System.out.println(price);
		System.out.println(quantity);
		
		ds.updatedrug(drug_id, price, quantity);
		 model.addAttribute("adminname", adminsname);
	        List<Drug> allDrugs = dao.allDrugs();
	        model.addAttribute("all", allDrugs);
	        model.addAttribute("drugmsg","Drug Updated  successfully");
	      //  ds.save(d);
		return "adminhomepage.html";
	}
	@Autowired
	AdminsController adc;
	@GetMapping("/adminpasswordupdate")
	public String adminpasswordupdate()
	{
		return "adminpasswordupdate.html";
	}
	
	@GetMapping("/updatepassword")
	public String Updatepassword(String email,String password,Model model)
	{
		int updateAdmin = adc.updateAdmin(email, password);
		if(updateAdmin>0)
		{
			model.addAttribute("success","Paswword updated succssfully");
			return "AdminLogin.html";
		}else
		{
			model.addAttribute("notsuccess","Password is not Updated");
			return "adminpasswordupdate.html";
		}
		
	}
	
	@GetMapping("/aboutus")
	public String aboutus()
	{
		return "PharamAbout.html";
	}
	
	@GetMapping("/contactus")
	public String contactus()
	{
		return "contactus.html";
	}
	
	
	@PostMapping("/order") // Use POST instead of GET since you are processing a request body
	public String orderplaced(
	    @RequestParam("drugId") int drugId, // From query parameters
	    @RequestParam("drugName") List<String> drugName,// From request body
	    @RequestParam("drugPrice") double drugPrice, // From query parameters
	    @RequestParam("dName") String dName, // From query parameters
	    Orders ord, // From request body
	    Model model // Not part of the request body
	) { 

	    LocalDateTime d = LocalDateTime.now();
	    DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-DD");
	    String format = d.format(ofPattern);

	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
	        java.util.Date date =sdf.parse(format);
	        ord.setOrderdate(LocalDate.now());
	        
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	   
	    pc.orderdrugs(mid, drugName);
	    ord.setPrice(drugPrice);
	    ord.setPname(dName);
	    ord.setMemberId(mid);
	    oc.orderplaced(ord);
	    

	    Random r = new Random();
	    int n = r.nextInt(1000000) + 1000000;

	    model.addAttribute("orderid", n);
	    model.addAttribute("date", format);
	    model.addAttribute("drugName", dName);
	    model.addAttribute("drugPrice", drugPrice);
	    model.addAttribute("username", uname); // Replace with actual username if available
	   
	    return "orderplaced.html";
	   
	}

	@GetMapping("/yourorders")
	public String Allorderdetails(Model model)
	{
		List<Payments> allorderdetails = pr.findById(mid);
		model.addAttribute("orders",allorderdetails);
		return "";
		
	}
}
