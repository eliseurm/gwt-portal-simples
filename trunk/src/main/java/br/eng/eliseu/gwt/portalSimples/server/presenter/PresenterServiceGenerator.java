package br.eng.eliseu.gwt.portalSimples.server.presenter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class PresenterServiceGenerator extends Generator {

	public final static Integer TAMANHO_MAXIMO_LOTE = 10;
	private Map<String, List<String>> sistemaPresenter = new HashMap<String, List<String>>();
	
	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
		String endGeneratedName = "Generated";
		
		JClassType classType;
		try {
			JClassType[] jClassTypes = context.getTypeOracle().getTypes();
			for (JClassType jClassType : jClassTypes) {
				if(jClassType.isAbstract() || jClassType.getName().contains("MainPresenter"))
					continue;
				JClassType superClass = jClassType.getSuperclass();
				if(superClass != null) {
					if( superClass.getName().endsWith("Presenter") ) {
						addPresenterToSistema(jClassType.getQualifiedSourceName());
					}
				}
			}
			
			boolean firstIf = true;
			StringBuffer ifStatement = new StringBuffer();
			StringBuffer ifBody = new StringBuffer();
			boolean firstIfBody = true;
			for(String sistema : sistemaPresenter.keySet()) {
				List<String> presenters = sistemaPresenter.get(sistema);
				int loteCount = 1;
				for(int i = 0; i < presenters.size(); i++) {
					String presenterName = presenters.get(i);

					if(firstIfBody) {
						ifBody.append("					if( ");
						firstIfBody = false;
					} else {
						ifBody.append("else if( ");
					}
					ifBody.append("\""+presenterName+"\".equals(presenterName) ) { \n");
					String viewName = presenterName.replace("Presenter", "View");
					ifBody.append("						"+viewName+" display = new " + viewName +"(); \n");
					ifBody.append("						"+presenterName+" presenter = new " +presenterName+ "(display, recurso);\n");
					ifBody.append("						presenter.go();\n");
					ifBody.append("						handler.finished(presenter);\n");
					ifBody.append("					}");
					
					
					String ifStatementTemp = "";
					if(loteCount == 1) {
						if(firstIf) {
							firstIf = false;
							ifStatementTemp = "		if(";
						} else {
							ifStatementTemp = " else if(";
						}
					} else {
						ifStatementTemp = "\n		   || ";
					}
					ifStatementTemp = ifStatementTemp + "\""+presenterName+"\".equals(presenterName) "; 
					ifStatement.append(ifStatementTemp);
					if(loteCount == TAMANHO_MAXIMO_LOTE || (i == presenters.size()-1)) {
						ifStatement.append(") { \n");
						ifStatement.append("			GWT.runAsync(new RunAsyncCallback() { \n");
						ifStatement.append("				public void onSuccess() { \n");
						ifStatement.append(ifBody.toString() + "\n");
						ifStatement.append("				} \n");
						ifStatement.append("    			public void onFailure(Throwable error) { \n");
						ifStatement.append("	 				handler.failure(error); \n");
						ifStatement.append("	 			} \n");
						ifStatement.append("			}); \n");
						ifStatement.append("		}");
						loteCount = 1;
						firstIfBody = true;
						ifBody = new StringBuffer();
					} else {
						loteCount++;
					}
				}
			}

			
			classType = context.getTypeOracle().getType(typeName);
			String simpleName = classType.getSimpleSourceName() + endGeneratedName;
			
			SourceWriter src = getSourceWriter(simpleName, classType, context, logger);
			if(src != null) {
				src.println("	private String presenterName = new String();");
				src.println("	private final PortalResource recurso;");
				src.println("");
				src.println("	public "+simpleName+"() {");
				src.println("		PortalGinjector injector = InjectorFactory.getInjector();");
				src.println("		this.recurso = injector.getPortalResource();");
				src.println("	}");
				src.println("");
				src.println("	public void downloadPresenter(final PresenterMenuEnum presenterCode, final DownloadHandler handler) {");
				src.println("		setPresenterName( presenterCode.getPresenterName() );");

				src.println(ifStatement.toString());
											
				if(!firstIf) {
					src.println("		else { ");
					src.println("			handler.failure(new RuntimeException(\"Não consegui achar o presenter \"+getPresenterName()+\", verifique o nome em PresenterMenuEnum\")); ");
					src.println("		}");
				}
				src.println("	}");
				src.println("");
				
				src.println("	public String getPresenterName() {");
				src.println("		return presenterName;");
				src.println("	}");
				src.println("");
				src.println("	public void setPresenterName(String presenterName) {");
				src.println("		this.presenterName = presenterName;");
				src.println("	}");
				src.println("");
				
				src.commit(logger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return typeName + endGeneratedName;		
	}

	public void addPresenterToSistema(String presenterName) {
		String sistema = getSistema(presenterName);
		if(sistema == null || "".equals(sistema)) {
			sistema = "notfound";
		}
		List<String> lstPresenters = sistemaPresenter.get(sistema);
		if(lstPresenters == null) {
			lstPresenters = new ArrayList<String>();
			sistemaPresenter.put(sistema, lstPresenters);
		}
		lstPresenters.add(presenterName);
	}

	private String getSistema(String presenterName) {
		String sistema = presenterName.replace("br.eng.eliseu.gwt.portalSimples.client.", "");
		return sistema.substring(0, sistema.indexOf("."));
	}
	
	public SourceWriter getSourceWriter(String simpleName, JClassType classType, GeneratorContext context, TreeLogger logger) {
		String packageName = classType.getPackage().getName();
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);

		composer.addImport("com.google.gwt.core.client.GWT");
		composer.addImport("com.google.gwt.core.client.RunAsyncCallback");
		composer.addImport("com.google.inject.Inject");
		composer.addImport("br.eng.eliseu.gwt.portalSimples.gin.InjectorFactory");
		composer.addImport("br.eng.eliseu.gwt.portalSimples.gin.PortalGinjector");
		composer.addImport("br.eng.eliseu.gwt.portalSimples.client.handle.DownloadHandler");
		composer.addImport("br.eng.eliseu.gwt.portalSimples.client.mvp.PortalResource");
		
		composer.setSuperclass(classType.getName());
		composer.addImplementedInterface("br.eng.eliseu.gwt.portalSimples.client.mvp.presenter.PresenterService");

		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);
		if (printWriter == null) {
			return null;
		} else {
			SourceWriter sw = composer.createSourceWriter(context, printWriter);
			return sw;
		}
	}	

}
