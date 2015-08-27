/**
 * 
 */
package com.sh.manage.module.page;

import java.io.Serializable;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.sh.manage.module.dbhelper.DBHelper;

/**
 * 
 * <p>Ztree帮助类</p>
 * @author 
 *
 */
public class ZTreeNode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2519528536193705791L;
	private int id;
    private String name;
    private String code;
    private int parentId;
    private String parentName;
    private String iconTag;//图标
    private String menuUrl;//资源url
    private boolean checked;//是否已经选择
    private int hasChild;//1 存在，0 不存在
    private List<ZTreeNode> children;
    
    public ZTreeNode(){
    	
    }
    
    public ZTreeNode(int id, String name, int parentId, String parentName,String iconTag,
			int hasChild) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.parentName = parentName;
		this.hasChild = hasChild;
		this.iconTag = iconTag;
	}

	public ZTreeNode(int id) {
        this.id = id;  
        if (!init()) {
            this.id = -1;  
        }
    }  
    // 初始化节点  
    public boolean init() {
        String sql = "select * from t_sys_menu where id =" + getId();
        boolean flag = false;
        try {
        	ResultSet rs = null;
			DBHelper dbHelper = new DBHelper();
			dbHelper.setSql(sql);
			rs = (ResultSet) dbHelper.executeQueryRs();
			if (rs.next()) {
              this.parentId = rs.getInt("menu_pid");  
              this.name = rs.getString("menu_name");  
              //this.parentName = rs.getString("parentname");  
              this.hasChild = 0;  
              flag = true;  
          }
		} catch (Exception e) {
			e.printStackTrace();
		}
//        Connection conn = null;  
//        Statement st = null;  
//          
//        try {  
//            conn = DBUtil.getConnection();  
//            st = conn.createStatement();  
//              
//            if (rs.next()) {  
//                this.parentId = rs.getInt("parentid");  
//                this.name = rs.getString("name");  
//                this.parentName = rs.getString("parentname");  
//                this.isHasChild = canExpand();  
//                flag = true;  
//            }  
//            rs.close();  
//            st.close();  
//            conn.close();  
//        } catch (SQLException e) {  
//            e.printStackTrace();  
//            return false;  
//        }  
        return flag;  
    }  
    // 是否可以展开  
    public boolean canExpand() {
        boolean flag = false;  
        String sql = "select * from t_sys_menu where menu_pid=" + getId();
        try {
        	ResultSet rs = null;
			DBHelper dbHelper = new DBHelper();
			dbHelper.setSql(sql);
			rs = (ResultSet) dbHelper.executeQueryRs();
			if (rs.next()) {
				flag = true;  
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
//        Connection conn = null;  
//        Statement st = null;  
//        ResultSet rs = null;  
//        try {  
//            conn = DBUtil.getConnection();  
//            st = conn.createStatement();  
//            rs = st.executeQuery(sql);  
//            if (rs.next()) {  
//                flag = true;  
//            }  
//            rs.close();  
//            st.close();  
//            conn.close();  
//        } catch (SQLException e) {  
//            e.printStackTrace();  
//        }  
        return flag;  
    }  
    // 取得子节点列表  
//    public List<ZTreeNode> getChildren() {
//        String sql = "select * from t_sys_menu where menu_pid=" + getId();
//        List<ZTreeNode> children = new ArrayList<ZTreeNode>();  
//        try {
//        	ResultSet rs = null;
//    		DBHelper dbHelper = new DBHelper();
//    		dbHelper.setSql(sql);
//    		rs = (ResultSet) dbHelper.executeQueryRs();
//    		while (rs.next()) {  
//    		      ZTreeNode zt = new ZTreeNode();  
//    		      zt.setId(rs.getInt("id"));  
//    		      zt.setHasChild(1);  
//    		      zt.setName(rs.getString("menu_name"));  
//    		      zt.setParentId(rs.getInt("menu_pid"));  
//    		      //zt.setParentName(rs.getString("parentname"));  
//    		      children.add(zt);  
//    		}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////        Connection conn = null;  
////        Statement st = null;  
////        ResultSet rs = null;  
////        
////        try {  
////            conn = DBUtil.getConnection();  
////            st = conn.createStatement();  
////            rs = st.executeQuery(sql);  
////            while (rs.next()) {  
////                TreeNode t = new TreeNode();  
////                t.setId(rs.getInt("id"));  
////                t.setIsHasChild(t.canExpand());  
////                t.setName(rs.getString("name"));  
////                t.setParentId(rs.getInt("parentid"));  
////                t.setParentName(rs.getString("parentname"));  
////                children.add(t);  
////            }  
////            rs.close();  
////            st.close();  
////            conn.close();  
////        } catch (SQLException e) {  
////            e.printStackTrace();  
////            return null;  
////        }  
//        return children;  
//    }  
    
    
    
    /**
     * 追加子树
     * @param id
     * @param name
     * @param parentId
     * @param parentName
     * @param hasChild
     * @param children
     * @return
     */
	public ZTreeNode appendChild(int id, String name, int parentId, String parentName,String iconTag,
			int hasChild, ZTreeNode parent) {
		ZTreeNode child = new ZTreeNode(id,name,parentId,parentName,iconTag,hasChild);
		//ztreeNode.add(child);
		return child;
	}
	
	
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getParentId() {  
        return parentId;  
    }  
    public void setParentId(int parentId) {  
        this.parentId = parentId;  
    }  
    public String getParentName() {  
        return parentName;  
    }  
    public void setParentName(String parentName) {  
        this.parentName = parentName;  
    }  
    public void setId(int id) {  
        this.id = id;  
    }  
    public int getId() {  
        return id;  
    }  

	public void setChildren(List<ZTreeNode> children) {
		this.children = children;
	}

	public String getIconTag() {
		return iconTag;
	}

	public void setIconTag(String iconTag) {
		this.iconTag = iconTag;
	}

	public int getHasChild() {
		return hasChild;
	}

	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}

	public List<ZTreeNode> getChildren() {
		return children;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
